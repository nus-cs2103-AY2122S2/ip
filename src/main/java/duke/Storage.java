package duke;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.util.ArrayList;


/**
 * Represents a storage that loads and save user data
 */
public class Storage {
    //@@author SimJM-reused
    //Reused from https://github.com/garethkoh/ip/blob/master/src/main/java/Storage.java
    // with minor modifications
    private String currentDir = System.getProperty("user.dir");
    private Path currentPath = Path.of(currentDir + File.separator + "data");
    private final File folderPath = new File(currentPath.toString());
    private final File dataPath = new File(currentPath.toString() + File.separator + "duke.txt");

    /**
     * Constructor for a Storage object
     */
    public Storage(){}

    /**
     * <p>Method to Suppress unchecked typecasts. Since the file path has been pre-set in this programme, we can be sure
     * that there will be no errors when typecasting. </p>
     * @param obj The object to be typecasted.
     * @param <T> The type to be casted to.
     * @return An Object of type T.
     */
    @SuppressWarnings("unchecked")
    public <T> T castToAnything(Object obj) {
        return (T) obj;
    }

    /**
     * Loads the users task list from file
     */
    @SuppressWarnings("Unchecked")
    public ArrayList<Task> readFile() {
        ArrayList<Task> toDoList = new ArrayList<>();
        try {
            if (folderPath.mkdir()) {
                System.out.println("Folder is created!");
            } else {
                System.out.println("Folder already exists.");
            }

            if (dataPath.createNewFile()) {
                System.out.println("File is created!");
            } else {
                System.out.println("File already exists.");
                try {
                    FileInputStream reader = new FileInputStream(dataPath);
                    ObjectInputStream listInput = new ObjectInputStream(reader);
                    toDoList = castToAnything(listInput.readObject());
                    // for (int i = 0; i < toDoList.size(); i++) {
                    //     TaskBank.getBank().add(toDoList.get(i));
                    // }
                    System.out.println("file read and data transferred");
                    listInput.close();
                    reader.close();
                } catch (ClassNotFoundException e) {
                    System.out.println("class not found");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return toDoList;
    }
    /**
     * Save the users tasklist to file
     */
    public void writeToFile(ArrayList<Task> bank) {
        try {
            FileOutputStream writer = new FileOutputStream(dataPath);
            ObjectOutputStream saveList = new ObjectOutputStream(writer);
            saveList.writeObject(bank);
            saveList.close();
            System.out.println("saved successfully!");
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        } catch (IOException e) {
            System.out.println("unable to write file");
        }
    }
    //@@author
}
