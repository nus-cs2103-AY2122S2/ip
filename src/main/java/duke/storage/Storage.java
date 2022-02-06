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

/**
 * Represents a storage tool in-charge of reading, writing and appendign to the database.
 * A <code>Storage</code> is further represented by the databasePath that allows and
 * facilitate connection to the database file e.g., <code>"./src/database.txt"</code>.
 */
public class Storage {
    private final String databasePath;

    public Storage(String databasePath) {
        this.databasePath = databasePath;
    }

    /**
     * Returns a boolean based on whether the database was successfully re-written.
     * If the method executed successfully, it returns true, else it returns false.
     *
     * @param textToAdd Text that will re-write the database.
     * @return a boolean value indicating the success of writing to the database.
     * @throws FileNotFoundException If database file can not be found.
     */
    public boolean writesToDatabase(String textToAdd) throws FileNotFoundException {
        try {
            FileWriter fw = new FileWriter(this.databasePath);
            fw.write(textToAdd);
            fw.close();
            return true;
        } catch (IOException err) {
            System.out.println("    An unexpected error writing to the database.");
        }
        return false;
    }

    /**
     * Returns a boolean based on whether the database was successfully appended.
     * If the method executed successfully, it returns true, else it returns false.
     *
     * @param textToAppend Text that will re-write the database.
     * @return a boolean value indicating the success of writing to the database.
     * @throws FileNotFoundException If database file can not be found.
     * @throws IOException If writing to the database was unsuccessful.
     */
    public boolean appendsToDatabase(String textToAppend) throws IOException {
        try {
            FileWriter fw = new FileWriter(databasePath, true); // Append instead of rewriting over
            fw.write(textToAppend);
            fw.close();
            return true;
        } catch (IOException err) {
            System.out.println("    An unexpected error appending to the database file.");
        }
        return false;
    }

    /**
     * Returns an arraylist of all the <code>Tasks</code> stored within the database.
     * If the method is successful, it will return an arraylist with <code>Tasks</code>,
     * else it returns one that is empty.
     *
     * @return an arraylist with all the Tasks stored in the database.
     * @throws FileNotFoundException if database file cannot be found.
     */
    ArrayList<Tasks> returnsAllTasks() throws FileNotFoundException {
        ArrayList<Tasks> taskList = new ArrayList<Tasks>();
        try {
            File f = new File(databasePath);
            Scanner sc = new Scanner(f);

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
        } catch (FileNotFoundException err) {
            System.out.println("    The database got corrupted by unrecognisable file formats.");
        }
        return taskList;
    }

    /**
     * Load all the <code>Tasks</code> from the database using other methods.
     * Returns an arraylist of all the <code>Tasks</code> stored within the database.
     * If the method is successful, it will return an arraylist with <code>Tasks</code>,
     * else it returns one that is empty.
     *
     * @return an arraylist with all the Tasks stored in the database.
     * @throws FileNotFoundException if database file cannot be found.
     */
    public ArrayList<Tasks> load() throws FileNotFoundException {
        ArrayList<Tasks> taskList = new ArrayList<Tasks>();
        try {
            taskList.addAll(returnsAllTasks());
        } catch (FileNotFoundException error) {
            System.out.println("    Database loading failed.");
        }
        return taskList;
    }

    public String getDatabasePath() {
        return databasePath;
    }
}
