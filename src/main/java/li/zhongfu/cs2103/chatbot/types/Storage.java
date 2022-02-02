package li.zhongfu.cs2103.chatbot.types;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputFilter;
import java.io.ObjectInputFilter.FilterInfo;
import java.io.ObjectInputFilter.Status;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import li.zhongfu.cs2103.chatbot.exceptions.StorageException;

/**
 * A class that presents an interface for loading and storing serializable objects to a file.
 */
public class Storage {
    private static Logger logger = Logger.getLogger(Storage.class.getName());

    private final File dataFile;
    private final ObjectInputFilter objectFilter;

    /**
     * Creates a new Storage instance with the given data file path and object filter.
     *
     * @param filePath the path to load objects from, or save objects to
     * @param objectFilter the ObjectInputFilter to use when loading objects from file
     */
    public Storage(String filePath, ObjectInputFilter objectFilter) {
        this.dataFile = new File(filePath);
        this.objectFilter = objectFilter;
    }

    /**
     * Creates a new Storage instance with the given data file path.
     *
     * @param filePath the path to load objects from, or save objects to
     */
    public Storage(String filePath) {
        this(filePath, TaskFilter::checkInput);
    }

    /**
     * Loads objects from the configured file path.
     *
     * @return a list of loaded objects
     * @throws FileNotFoundException if the configured file path does not exist
     * @throws StorageException if there was any error while loading objects, e.g. objects rejected by filter
     */
    public List<Object> load() throws FileNotFoundException, StorageException {
        List<Object> objs = new ArrayList<>();

        try (FileInputStream fileStream = new FileInputStream(this.dataFile);
                ObjectInputStream objectStream = new ObjectInputStream(fileStream)) {
            objectStream.setObjectInputFilter(this.objectFilter);

            while (true) {
                Object o = objectStream.readObject();
                if (o != null) {
                    objs.add(o);
                } else {
                    logger.warning("Got null in objectStream, ignoring");
                }
            }
        } catch (EOFException e) { // also means objectStream has no more objects, return normally
            return objs;
        } catch (FileNotFoundException e) { // throw FileNotFoundException instead of IOException/StorageException
            throw e;
        } catch (IOException e) {
            throw new StorageException("Got IOException while trying to load objects", e);
        } catch (ClassNotFoundException e) {
            throw new StorageException("Unknown class found in storage!", e);
        }
    }

    /**
     * Saves objects to the configured file path.
     *
     * @param objs a list of Serializable objects to save
     * @throws IOException if there was an error while saving objects
     */
    public void save(List<? extends Object> objs) throws IOException {
        File parent = this.dataFile.getParentFile();
        parent.mkdirs(); // attempt to make all parent dirs, and ignore if already exists

        try (FileOutputStream fileStream = new FileOutputStream(this.dataFile);
                ObjectOutputStream objectStream = new ObjectOutputStream(fileStream)) {
            for (Object o : objs) {
                objectStream.writeObject(o);
            }
        }
    }
}

// https://docs.oracle.com/javase/10/core/serialization-filtering1.htm#JSCOR-GUID-0A1D23AB-2F18-4979-9288-9CFEC04F207E
class TaskFilter {
    private static final String[] ALLOWED_PACKAGES = {
        "java.time",
        "li.zhongfu.cs2103.chatbot.types.tasks"
    };

    private TaskFilter() {
    }

    static Status checkInput(FilterInfo filterInfo) {
        Class<?> cls = filterInfo.serialClass();
        if (cls != null) {
            String packageName = cls.getPackageName();
            return Arrays.stream(ALLOWED_PACKAGES).anyMatch(packageName::equals) ? Status.ALLOWED : Status.REJECTED;
        }
        return Status.UNDECIDED;
    }
}
