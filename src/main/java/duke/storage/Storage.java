package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Tasks;
import duke.task.Todos;
import duke.ui.Ui;

/**
 * Represents a storage tool in-charge of reading, writing and appendign to the database.
 * A Storage is further represented by the databasePath that allows and
 * facilitate connection to the database file e.g., "./src/database.txt".
 */
public class Storage {
    private static File databaseFile;

    /**
     * The sole constructor of the Storage class.
     */
    public Storage() {
        File databaseFolder = new File("./data");
        if (!databaseFolder.exists()) {
            databaseFolder.mkdir();
        }

        File databaseFile = new File("./data/DukeDatabase.txt");
        try {
            if (!databaseFile.exists()) {
                databaseFile.createNewFile();
            }
        } catch (IOException err) {
            Ui.showErrorMessage(err, "Storage creation failed");
        }
        System.out.println(databaseFile.getAbsolutePath());
        this.databaseFile = databaseFile;
    }

    /**
     * Aid in the creation of a database file in the case where one was not created.
     */
    public void createDatabaseFile() {
        File databaseFolder = new File("./data");
        if (!databaseFolder.exists()) {
            databaseFolder.mkdir();
        }

        File databaseFile = new File("./data/DukeDatabase.txt");
        try {
            if (!databaseFile.exists()) {
                databaseFile.createNewFile();
            }
        } catch (IOException err) {
            Ui.showErrorMessage(err, "Storage creation failed");
        }
        System.out.println(databaseFile.getAbsolutePath());
        this.databaseFile = databaseFile;
    }

    /**
     * Writes or appends to the database.
     *
     * @param textToAppend Text to append or write in the database.
     * @param isAppendOrWrite Denote whether to write or append to the database.
     * @throws IOException When the FileWriter has an IOException error.
     */
    public void writeToDatabase(String textToAppend, boolean isAppendOrWrite) throws IOException {
        FileWriter fw = new FileWriter(databaseFile, isAppendOrWrite); // Append instead of rewriting over
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * It acts as a handler facilitating the writing to the database.
     *
     * @param textToAdd Text to be written into the database.
     * @return a boolean value indicating the status of writing to the database.
     */
    public boolean hasWriteToDatabase(String textToAdd) {
        assert databaseFile != null : "The database file could not be found. Try PokeJournal again later";
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
     * It acts as a handler facilitating the appending to the database.
     *
     * @param textToAppend Text to append to the database.
     * @return a boolean value indicating the status of appending to the database.
     */
    public boolean hasAppendToDatabase(String textToAppend) {
        assert databaseFile != null : "The database file could not be found. Try PokeJournal again later";
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
     * Return a collection of tasks available in the storage.
     *
     * @return an arraylist with all the Tasks stored in the database.
     */
    ArrayList<Tasks> returnsAllTasks() throws FileNotFoundException {
        ArrayList<Tasks> taskList = new ArrayList<Tasks>();
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
     * Load all the Tasks from the database into a collection.
     *
     * @return an arraylist with all the Tasks stored in the database.
     */
    public ArrayList<Tasks> load() {
        try {
            return new ArrayList<Tasks>(returnsAllTasks());
        } catch (FileNotFoundException err) {
            Ui.showErrorMessage(err, "load");
            return new ArrayList<Tasks>();
        }
    }
}
