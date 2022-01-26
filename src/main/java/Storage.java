import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;

public class Storage {
    protected String filePath;
    protected ArrayList<Task> tasks;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        try {
            FileInputStream fis = new FileInputStream(this.filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            TaskList taskList = (TaskList) ois.readObject();
            this.tasks = taskList.getTasks();
            ois.close();
        } catch (FileNotFoundException | ClassNotFoundException e) {
            File f = new File(this.filePath);
            f.getParentFile().mkdirs();
            try {
                f.createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            this.tasks = new ArrayList<>();
        } catch (EOFException e) {
            throw new DukeException("Corrupted or invalid file. Please delete data/duke.txt and try again.");
        } catch (IOException e) {
            throw new DukeException("Input Stream could not read data/duke.txt file.");
        }
        return tasks;
    }

    public void store(TaskList tasks) throws DukeException {
        try {
            FileOutputStream fos = new FileOutputStream(this.filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tasks);
            oos.close();
        } catch (IOException e) {
            throw new DukeException("Unable to write into data/duke.txt.");
        }
    }
}
