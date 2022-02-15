package bobby;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import bobby.exception.StorageException;
import bobby.task.Task;

public class Storage {
    private File bobbyFile;

    public Storage(File dataFile) {
        this.bobbyFile = dataFile;
    }

    /**
     * Loads the saved tasks.
     *
     * @return A list of tasks
     * @throws StorageException If loading fails
     * @throws FileNotFoundException If the file cannot be found at the specified location.
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Task> loadTasks() throws StorageException, FileNotFoundException {
        ArrayList<Task> tasks;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(bobbyFile));
            tasks = (ArrayList<Task>) ois.readObject();
            ois.close();
        } catch (ClassNotFoundException | FileNotFoundException e) {
            throw new StorageException("no_file");
        } catch (IOException e) {
            throw new StorageException("empty_file");
        }
        return tasks;
    }

    /**
     * Saves and stores the current list of tasks as a file.
     *
     * @param tasks The list of tasks to be stored.
     */
    public void saveTasks(ArrayList<Task> tasks) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(bobbyFile));
            oos.writeObject(tasks);
            oos.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
