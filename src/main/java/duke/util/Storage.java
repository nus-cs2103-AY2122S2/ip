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
    private final String fileName;

    /**
     * Class constructor.
     * Creates a new file if it does not already exist.
     *
     * @param filePath The filepath to the text file.
     * @param fileName The name of the text file.
     */
    public Storage(String filePath, String fileName) {
        this.filePath = filePath;
        this.fileName = fileName;
        createFileAndFolderIfDoesNotExist();
    }

    /**
     * Creates the text file and folder if they do not already exist.
     */
    public void createFileAndFolderIfDoesNotExist() {
        File folder = new File(filePath);
        try {
            if (!folder.exists()) {
                folder.mkdir();
            } else {
                File file = new File(filePath + fileName);
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
        File file = new File(filePath + fileName); // create a File for the given file path
        ArrayList<Task> currentTasks = new ArrayList<>();

        if (file.exists()) {
            try {
                Scanner s = new Scanner(file); // create a Scanner using the File as the source
                while (s.hasNext()) {
                    String task = s.nextLine();
                    String[] taskComponents = task.split(" [|] "); // [type, status, description, time(if any)]
                    Task t;

                    switch (taskComponents[0]) {
                    case "D":
                        t = new Deadline(taskComponents[2], Parser.parseDateTime(taskComponents[3]));
                        break;
                    case "E":
                        t = new Event(taskComponents[2], Parser.parseDateTime(taskComponents[3]));
                        break;
                    default:
                        t = new Todo(taskComponents[2]);
                        break;
                    }
                    currentTasks.add(t);

                    if (taskComponents[1].equals("1")) {
                        t.markAsDone();
                    }
                }
            } catch (IOException e) {
                throw new DukeException("Unable to load saved tasks.");
            }
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
            FileWriter fw = new FileWriter(filePath + fileName);
            fw.write(formattedTaskList);
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Unable to save changes to hard disk :(");
        }
    }
}
