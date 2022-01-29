package duke.storage;

import duke.task.Task;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that contains methods to save and load a list of tasks to a specific file path.
 */
public class Storage {
    private final String FILE_PATH;
    private final File FILE;

    /**
     * Constructor of the Storage class.
     *
     * @param filePath File path to read from and write to.
     */
    public Storage(String filePath){
        this.FILE_PATH = filePath;
        this.FILE = new File(FILE_PATH);

        try {
            if (!FILE.exists()) {
                FILE.getParentFile().mkdirs();
                FILE.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Something went wrong when creating initialising the file: "
                + e.getMessage());
        }
    }

    /**
     * Loads the list of tasks from the file path.
     * Will return a new ArrayList if the file is not found.
     *
     * @return List of tasks.
     */
    public List<Task> load(){
        List<Task> result = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(FILE_PATH);
            ObjectInputStream ois = new ObjectInputStream(fis);
            result = (List<Task>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            //System.out.println("Something went wrong while loading file: " + e.getMessage());
        }
        return result;
    }

    /**
     * Saves a given list of tasks to the file path.
     *
     * @param taskList List of tasks to be saved.
     * @return True if the list was saved successfully, false otherwise.
     */
    public boolean save(List<Task> taskList){
        boolean isSuccessful = false;
        try {
            FileOutputStream fos = new FileOutputStream(FILE_PATH);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(taskList);
            oos.close();
            isSuccessful = true;
        } catch (IOException e){
            System.out.println("Something went wrong while saving file: " + e.getMessage());
        }
        return isSuccessful;
    }
}
