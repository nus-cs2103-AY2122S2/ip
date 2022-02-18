package duke.modules;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * The Storage class manages user data.
 */
public class Storage {

    private static String directoryPath = System.getProperty("user.dir") + "/data/";
    private static String listFile = "data.txt";

    private static final File FOLDER_PATH = new File(directoryPath);
    private static final File DATA_PATH = new File(directoryPath + listFile);

    /**
     * Constructor for a Storage object.
     */
    public Storage(){
    }

    /**
     * <p>Method to Suppress unchecked typecasts. Since the file path has been pre-set in this programme, we can be sure
     * that there will be no errors when typecasting. </p>
     * @param obj The object to be typecasted.
     * @param <T> The type to be casted to.
     * @return An Object of type T.
     */
    @SuppressWarnings("unchecked")
    public static <T> T castToAnything(Object obj) {
        return (T) obj;
    }

    /**
     * Loads the users task list from memory.
     *
     * @return The latest copy of the users task list.
     */
    public static ArrayList<Task> load() {
        ArrayList<Task> toDoList = new ArrayList<>();
        if (existsSaveFile()) {
            return loadData();
        } else {
            return toDoList;
        }
    }


    /**
     * Checks if a storage file exists. If not, create the file.
     *
     * @return True if file exists, False if not exists.
     */
    public static boolean existsSaveFile() {
        boolean hasExecuted = false;
        try {

            if (FOLDER_PATH.mkdirs()) {
                Ui.print("Folder is created!\n");
            } else {
                Ui.print("Folder already exists.\n");
            }
            if (DATA_PATH.createNewFile()) {
                Ui.print("File is created!\n");
                return false;
            } else {
                Ui.print("File already exists.\n");
                return true;
            }
        } catch (IOException e) {
            hasExecuted = true;
            // Assume catch statement will never be triggered
            assert !hasExecuted;
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Loads data from a previous save file.
     *
     * @return The saved ArrayList.
     */
    public static ArrayList<Task> loadData() {
        ArrayList<Task> toDoList = new ArrayList<>();
        boolean hasExecuted = false;
        try {
            FileInputStream reader = new FileInputStream(DATA_PATH);
            ObjectInputStream listInput = new ObjectInputStream(reader);
            toDoList = castToAnything(listInput.readObject());
            listInput.close();
            reader.close();
            return toDoList;
        } catch (ClassNotFoundException e) {
            hasExecuted = true;
            assert !hasExecuted;
            Ui.print("class not found\n");
            return null;
        } catch (IOException e) {
            hasExecuted = true;
            assert !hasExecuted;
            Ui.print("IOException\n");
            return null;
        }

    }

    /**
     * Saves the users task list to memory.
     *
     * @param taskList The list of tasks to be saved.
     */
    public static void save(TaskList taskList) {
        try {
            FileOutputStream writer = new FileOutputStream(DATA_PATH);
            ObjectOutputStream saveList = new ObjectOutputStream(writer);
            saveList.writeObject(taskList.getToDoList());
            saveList.close();
            Ui.print("your list has been saved!\n");
        } catch (FileNotFoundException e) {
            Ui.print("file not found");
        } catch (IOException e) {
            Ui.print("failed to write to file");
        }
    }
}
