package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Tasks;
import duke.task.Todos;
import duke.ui.Ui;

/**
 * Represents a storage tool in-charge of reading, writing and appendign to the database.
 * A <code>Storage</code> is further represented by the databasePath that allows and
 * facilitate connection to the database file e.g., <code>"./src/database.txt"</code>.
 */
public class Storage {
    private final String databasePath;

    public Storage() {
        Path dataFolderAbsPath = Paths.get("src/main/java/duke/data");
        boolean hasDirectoryExist = Files.exists(dataFolderAbsPath);
        if (!hasDirectoryExist) {
            new File(dataFolderAbsPath.toAbsolutePath().toString()).mkdir();
        }

        Path dataAbsPath = Paths.get("src/main/java/duke/data/DukeDatabase.txt");
        boolean hasDataFileExist = Files.exists(dataAbsPath);
        if(!hasDataFileExist) {
            new File(dataAbsPath.toAbsolutePath().toString());
        }

        this.databasePath = dataAbsPath.toAbsolutePath().toString();
        //this.databasePath = "C:/Users/benny/Desktop/Y2S2/CS2103T_Software_Engineer/"+ "Individual_Project/src/main/java/duke/data/DukeDatabase.txt";
    }

    public void createDatabaseFile() {
        Path dataFolderAbsPath = Paths.get("src/main/java/duke/data/");
        boolean hasDirectoryExist = Files.exists(dataFolderAbsPath);
        if (!hasDirectoryExist) {
            new File(dataFolderAbsPath.toString()).mkdir();
        }

        Path dataAbsPath = Paths.get("src/main/java/duke/data/DukeDatabase.txt");
        boolean hasDataFileExist = Files.exists(dataAbsPath);
        if(!hasDataFileExist) {
            new File(dataAbsPath.toString());
        }
    }

    public void writeToDatabase(String textToAppend, boolean isAppendOrWrite) throws IOException {
        FileWriter fw = new FileWriter(databasePath, isAppendOrWrite); // Append instead of rewriting over
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Returns a boolean based on whether the database was successfully re-written.
     * If the method executed successfully, it returns true, else it returns false.
     *
     * @param textToAdd Text that will re-write the database.
     * @return a boolean value indicating the success of writing to the database.
     */
    public boolean hasWriteToDatabase(String textToAdd) {
        try {
            writeToDatabase(textToAdd, false);
        } catch (IOException err) {
            createDatabaseFile();
            Ui.showErrorMessage(err, "writesToDatabase");
            return false;
        }
        return true;
    }

    /**
     * Returns a boolean based on whether the database was successfully appended.
     * If the method executed successfully, it returns true, else it returns false.
     *
     * @param textToAppend Text that will re-write the database.
     * @return a boolean value indicating the success of writing to the database.
     */
    public boolean hasAppendToDatabase(String textToAppend) {
        try {
            writeToDatabase(textToAppend, true);
        } catch (IOException err) {
            createDatabaseFile();
            Ui.showErrorMessage(err, "textToAppend");
            return false;
        }
        return true;
    }

    /**
     * Returns an arraylist of all the <code>Tasks</code> stored within the database.
     * If the method is successful, it will return an arraylist with <code>Tasks</code>,
     * else it returns one that is empty.
     *
     * @return an arraylist with all the Tasks stored in the database.
     */
    ArrayList<Tasks> returnsAllTasks() throws FileNotFoundException {
        ArrayList<Tasks> taskList = new ArrayList<Tasks>();
        File databaseFile = new File(databasePath);
        Scanner sc = new Scanner(databaseFile);
        while (sc.hasNextLine()) {
            String taskData = sc.nextLine();
            String[] taskDataSplit = taskData.split(" \\| ");
            switch (taskDataSplit[0]) {
            case "T":
                taskList.add(new Todos(taskDataSplit[2],
                        taskDataSplit[1].equals("X")));
                break;
            case "E":
                taskList.add(new Events(taskDataSplit[2],
                        taskDataSplit[1].equals("X"),
                        taskDataSplit[3]));
                break;
            case "D":
                taskList.add(new Deadlines(taskDataSplit[2],
                        taskDataSplit[1].equals("X"),
                        taskDataSplit[3]));
                break;
            default:
                break;
            }
        }
        sc.close();
        return taskList;
    }

    /**
     * Load all the <code>Tasks</code> from the database using other methods.
     * Returns an arraylist of all the <code>Tasks</code> stored within the database.
     * If the method is successful, it will return an arraylist with <code>Tasks</code>,
     * else it returns one that is empty.
     *
     * @return an arraylist with all the Tasks stored in the database.
     */
    public ArrayList<Tasks> load() {
        try {
            return new ArrayList<Tasks>(returnsAllTasks());
        } catch (FileNotFoundException err) {
            new Ui().showErrorMessage(err, "load");
            return new ArrayList<Tasks>();
        }
    }

    public String getDatabasePath() {
        return databasePath;
    }
}
