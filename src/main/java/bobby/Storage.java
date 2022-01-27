package bobby;

import bobby.exception.StorageException;
import bobby.task.Task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Storage {
    private File bobbyFile;

    public Storage(File dataFile) {
        this.bobbyFile = dataFile;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Task> loadTasks() throws StorageException, FileNotFoundException {
        ArrayList<Task> task;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(bobbyFile));
            task = (ArrayList<Task>) ois.readObject();
            ois.close();
        } catch (ClassNotFoundException | FileNotFoundException e) {
            throw new StorageException("no_file");
        } catch (IOException e) {
            throw new StorageException("empty_file");
        }
        return task;
    }

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
