package bob;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import bob.task.Task;

/**
 * Represents the file storage for the Bob program
 */
public class Storage {
    private static String fileName;
    private File store;

    /**
     * Constructor for the Storage class.
     * @param filePath the relative path to the file
     */
    public Storage(String filePath) {
        fileName = filePath;
        try {
            store = new File(filePath);
            if (!store.exists()) {
                store.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("File exception while creating new file");
        }
    }

    public static String getFileName() {
        return fileName;
    }

    /**
     * Updates the store of the program.
     * Rewrites the file with a new serialized task list.
     * @param taskList a List of tasks
     */
    public void updateStore(TaskList taskList) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(store));
            oos.writeObject(taskList.getList());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns the List of tasks that was saved in the store from previous program runs.
     * Deserializes the object from the file given and casts it to a List of tasks. This cast is safe as
     * there will be no other objects being written into the file.
     * @return a List of tasks
     */
    @SuppressWarnings("unchecked")
    public List<Task> getSavedStore() {
        try {
            FileInputStream fis = new FileInputStream(store);
            ObjectInputStream ois = new ObjectInputStream(fis);
            return (List<Task>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}
