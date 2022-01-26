package li.zhongfu.cs2103.chatbot;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputFilter.FilterInfo;
import java.io.ObjectInputFilter.Status;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import li.zhongfu.cs2103.chatbot.types.Task;

public class Storage {
    private static Logger logger = Logger.getLogger(Storage.class.getName());

    private static final File DATA_FILE = new File("data/tasks.dat");

    private Storage() {
    }

    public static List<Task> load() throws FileNotFoundException, StorageException {
        List<Task> tasks = new ArrayList<>();

        try (FileInputStream fileStream = new FileInputStream(DATA_FILE);
                ObjectInputStream objectStream = new ObjectInputStream(fileStream)) {
            objectStream.setObjectInputFilter(TaskFilter::checkInput);

            while (true) {
                Object o = objectStream.readObject();
                if (o instanceof Task) {
                    tasks.add((Task) o);
                } else {
                    logger.warning(() -> String.format("Unexpected class %s found in storage, ignoring",
                            o.getClass().getName()));
                }
            }
        } catch (EOFException e) { // also means objectStream has no more objects, return normally
            return tasks;
        } catch (FileNotFoundException e) { // throw FileNotFoundException instead of IOException/StorageException
            throw e;
        } catch (IOException e) {
            throw new StorageException("Got IOException while trying to load tasks", e);
        } catch (ClassNotFoundException e) {
            throw new StorageException("Unknown class found in storage!", e);
        }
    }

    public static void save(List<Task> tasks) throws IOException {
        File parent = DATA_FILE.getParentFile();
        parent.mkdirs(); // attempt to make all parent dirs, and ignore if already exists

        try (FileOutputStream fileStream = new FileOutputStream(DATA_FILE);
                ObjectOutputStream objectStream = new ObjectOutputStream(fileStream)) {
            for (Task task : tasks) {
                objectStream.writeObject(task);
            }
        }
    }
}

// https://docs.oracle.com/javase/10/core/serialization-filtering1.htm#JSCOR-GUID-0A1D23AB-2F18-4979-9288-9CFEC04F207E
class TaskFilter {
    private TaskFilter() {
    }

    static Status checkInput(FilterInfo filterInfo) {
        Class<?> cls = filterInfo.serialClass();
        if (cls != null) {
            return (Task.class.isAssignableFrom(cls)) ? Status.ALLOWED : Status.REJECTED;
        }
        return Status.UNDECIDED;
    }
}

class StorageException extends Exception {
    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}