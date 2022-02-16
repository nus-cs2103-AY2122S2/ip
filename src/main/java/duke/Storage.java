
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
     * Loads the users task list from file
     */
    @SuppressWarnings("Unchecked")
    public void readFile() {
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
                FileInputStream reader = new FileInputStream(dataPath);
                ObjectInputStream listInput = new ObjectInputStream(reader);
                try {
                    toDoList = (ArrayList<Task>) listInput.readObject();
                    for (int i = 0; i < toDoList.size(); i++) {
                        TaskBank.getBank().add(toDoList.get(i));
                    }
                    System.out.println("file read and data transferred");
                    listInput.close();
                } catch (ClassNotFoundException e) {
                    System.out.println("class not found");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Save the users tasklist to file
     */
    public void writeToFile() {
        try {
            FileOutputStream writer = new FileOutputStream(dataPath);
            ObjectOutputStream saveList = new ObjectOutputStream(writer);
            saveList.writeObject(TaskBank.getBank());
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
