package storage;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import exception.DukeException;
import task.Task;
import task.TaskList;

/**
 * Reads and Stores user tasks.
 * Facilitates caching feature by storing a file into the specified file path.
 */
public class Storage {
    protected String filePath;
    protected ArrayList<Task> tasks;

    /**
     * Class constructor.
     *
     * @param filePath File path to read/write.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads the information from a file indicated from the file path.
     * If an error is detected, throws duke.Duke exception to indicate
     * there was an issue processing the cached data.
     *
     * @return An ArrayList of tasks
     * @throws DukeException If detected file or File input stream is corrupted
     */
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
            throw new DukeException("Corrupted or invalid file. Please delete data/tasks.txt and try again.");
        } catch (IOException e) {
            throw new DukeException("Input Stream could not read data/tasks.txt file.");
        }
        return tasks;
    }

    /**
     * Saves the information from a file indicated from the file path.
     * If an error is detected, throws duke.Duke exception to indicate
     * there was an issue processing the cached data.
     *
     * @throws DukeException If unable to detect file
     */
    public void store(TaskList tasks) throws DukeException {
        try {
            FileOutputStream fos = new FileOutputStream(this.filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tasks);
            oos.close();
        } catch (IOException e) {
            throw new DukeException("Unable to write into data/tasks.txt.");
        }
    }
}
