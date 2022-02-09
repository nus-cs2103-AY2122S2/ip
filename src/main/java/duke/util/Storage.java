package duke.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents the storage function of the program. Deals with loading and saving tasks to a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Class constructor.
     * Creates a new file if it does not already exist.
     *
     * @param filePath The filepath to the text file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        createFileAndFolderIfDoesNotExist();
    }

    /**
     * Creates the text file and folder if they do not already exist.
     */
    public void createFileAndFolderIfDoesNotExist() {
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Unable to create file to save tasks.");
        }
    }

    /**
     * Returns the TaskList stored in the text file.
     *
     * @return ArrayList of tasks
     * @throws DukeException If the tasks cannot be loaded.
     */
    public ArrayList<Task> load() throws DukeException {
        File file = new File(filePath);
        ArrayList<Task> currentTasks = new ArrayList<>();

        if (!file.exists()) {
            throw new DukeException("Error loading tasks. File does not exist.");
        }

        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String task = s.nextLine();
                String[] taskComponents = task.split(" [|] "); // [type, status, description, time(if any)]
                String taskType = taskComponents[0];
                String status = taskComponents[1];
                String description = taskComponents[2];
                Task t;

                switch (taskType) {
                case "D":
                    String datetime = taskComponents[3];
                    t = new Deadline(description, Parser.parseDateTime(datetime));
                    break;
                case "E":
                    datetime = taskComponents[3];
                    t = new Event(description, Parser.parseDateTime(datetime));
                    break;
                case "T":
                    t = new Todo(description);
                    break;
                default:
                    throw new DukeException("Error loading tasks. Data saved in file has incorrect format.");
                }
                currentTasks.add(t);

                boolean isCompleted = status.equals("1");
                if (isCompleted) {
                    t.markAsDone();
                }
            }
        } catch (IOException e) {
            throw new DukeException("Unable to load saved tasks.");
        }

        return currentTasks;
    }

    /**
     * Saves the changes to TaskList to the text file.
     *
     * @param taskList The new TaskList to be stored.
     * @throws DukeException If changes cannot be saved to file.
     */
    public void saveToHardDisk(TaskList taskList) throws DukeException {
        try {
            String formattedTaskList = taskList.formatListForSaving();
            FileWriter fw = new FileWriter(filePath);
            fw.write(formattedTaskList);
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Unable to save changes to hard disk :(");
        }
    }
}
