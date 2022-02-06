package duke.storage;

import duke.DukeException;
import duke.tasks.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Stores the user data in a text file.
 */
public class Storage {
    private final File STORAGE_DIRECTORY = new File("data");
    private final File STORAGE_FILE = new File("data/tasks.txt");

    /**
     * Constructs a storage pointing to a text file.
     *
     * @throws DukeException If there is error when creating file.
     */
    public Storage() throws DukeException {
        if (!STORAGE_DIRECTORY.exists()) {
            STORAGE_DIRECTORY.mkdir();
        }

        try {
            STORAGE_FILE.createNewFile();
        } catch (IOException e) {
            throw new DukeException("Error creating storage file! You data will not be stored.", e);
        }
    }

    /**
     * Retrieves the task list store in the storage text file.
     *
     * @return The task list in storage file.
     */
    public ArrayList<Task> retrieveTaskList() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            Scanner sc = new Scanner(STORAGE_FILE);
            while (sc.hasNextLine()) {
                tasks.add(convertStringToTask(sc.nextLine()));
            }
        } catch (FileNotFoundException e) {
            return tasks;
        }
        return tasks;
    }

    /**
     * Stores the current tasks in program to the storage file.
     *
     * @param tasks The list of tasks currently in the program.
     * @throws DukeException When error writing to file.
     */
    public void saveTaskList(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(STORAGE_FILE);
            fw.write(tasks.taskListFileString());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Something went wrong when saving your data!", e);
        }
    }

    private Task convertStringToTask(String task) {
        char type = task.charAt(0);
        boolean isDone = task.charAt(1) == '1';
        String[] detail = task.substring(2).split(" \\| ");
        switch (type) {
        case 'T':
            return new ToDo(detail[0], isDone);
        case 'E':
            return new Event(detail[0], detail[1], isDone);
        case 'D':
            return new Deadline(detail[0], detail[1], isDone);
        default:
            return new Task("!!Looks like something went wrong with this task.");
        }
    }
}
