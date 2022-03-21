package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import exceptions.DukeException;
import tasks.Task;

/** A class that deals with the storage of Duke tasks onto the hard disk. */
public class Storage {

    /** Directory which stores the tasks on a .txt file. */
    private static final File DIRECTORY = new File("data");

    /** Path of the .txt file. */
    private static final Path TASKS_PATH = Paths.get("data", "tasks.txt");

    /** .txt file which stores the Duke tasks. */
    private static final File TASKS_FILE = new File(TASKS_PATH.toString());

    /**
     * Returns an ArrayList of tasks, if a valid .txt tasks file exist on disk.
     * Otherwise, returns an empty arraylist of tasks.
     *
     * @return An ArrayList of tasks.
     **/
    public ArrayList<Task> loadTasks() {

        ArrayList<Task> allTasks = new ArrayList<>();

        // adds directory if it does not exist
        boolean directoryCreated = DIRECTORY.mkdir();

        // creates a new file if it does not exist
        boolean didNotExist;
        try {
            didNotExist = TASKS_FILE.createNewFile();
        } catch (IOException err) {
            didNotExist = true; // just create from scratch
        }

        if (!directoryCreated && !didNotExist) { // the file existed
            try {
                Scanner s = new Scanner(TASKS_FILE); // create a Scanner using the File as the source
                Task t;
                while (s.hasNext()) {
                    try {
                        String someTask = s.nextLine();
                        t = Task.importFromString(someTask);
                        allTasks.add(t);
                    } catch (DukeException err) {
                        // ignore this task
                    }
                }
            } catch (FileNotFoundException err) {
                // will not reach here, since we checked that the file exists
            }
        }
        return allTasks;
    }

    /**
     * Takes in an ArrayList of tasks, and writes them to disk.
     *
     * @param allTasks an ArrayList of tasks.
     * @throws DukeException if the file was tampered with during the execution of the program.
     */
    public void saveTasks(ArrayList<Task> allTasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(TASKS_PATH.toString());
            for (Task t: allTasks) {
                fw.write(t.exportToString() + "\n");
            }
            fw.close();
        } catch (IOException err) {
            throw new DukeException(err.getMessage());
        }
    }
}
