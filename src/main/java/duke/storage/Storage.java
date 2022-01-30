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
    private final File directory = new File("data");
    private final File file = new File("data/tasks.txt");

    /**
     * Constructs a storage pointing to a text file.
     *
     * @throws DukeException If there is error when creating file.
     */
    public Storage() throws DukeException {
        if (!directory.exists()) {
            directory.mkdir();
        }

        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new DukeException("Error creating storage file! You data will not be stored.", e);
        }
    }

    /**
     * Retrieves the tasks store in the storage text file.
     *
     * @return The tasks stored in an array list.
     */
    public ArrayList<Task> retrieveTaskList() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                tasks.add(stringToTask(sc.nextLine()));
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
            FileWriter fw = new FileWriter(file);
            fw.write(tasks.taskListFileString());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Something went wrong when saving your data!", e);
        }
    }

    private Task stringToTask(String task) {
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
