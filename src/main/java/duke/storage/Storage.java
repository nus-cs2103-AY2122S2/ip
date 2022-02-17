package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.tasklist.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

public class Storage {


    private static final String HOME = System.getProperty("user.home");
    private static final java.nio.file.Path PATH = java.nio.file.Paths.get(Storage.HOME,
            "desktop", "ip", "data", "askjamie.txt");
    public static final String FILE_PATH = String.valueOf(PATH);
    public Storage() {
        Storage.checkValidPath();
        if (!java.nio.file.Files.exists(Storage.PATH)) {
            Storage.createFolder();
        }
    }
    /**
     * Reads existing task text file from local folder
     * If file does not exist, create new text file
     */
    public void readFile() {

        try {
            File file = new File(this.FILE_PATH);

            if (file.createNewFile()) {
                System.out.println("New file created at " + FILE_PATH);
            }
        } catch (IOException e) {
            System.out.println("Unable to create file " + FILE_PATH);
        }
    }

    /**
     * Save current task list into a text file
     *
     * @param tasks the list of tasks
     * @throws DukeException if TaskList instance from user is invalid
     */
    public static void saveData(TaskList tasks) {
        Storage.checkValidPath();
        try {
            FileWriter fw = new FileWriter(FILE_PATH);

            for (Task currTask : tasks.getTasks()) {
                String description = currTask.getDescription();
                int isComplete = currTask.isComplete() ? 1 : 0;
                String saveText;

                if (currTask instanceof ToDo) {
                    saveText = "T | "
                            + isComplete + " | "
                            + description;
                } else if (currTask instanceof Deadline) {
                    saveText = "D | "
                            + isComplete + " | "
                            + description + " | "
                            + ((Deadline) currTask).getDeadlineBy();
                } else if (currTask instanceof Event) {
                    saveText = "E | "
                            + isComplete + " | "
                            + description + " | "
                            + ((Event) currTask).getEventBy();
                } else {
                    fw.close();
                    throw new DukeException("Invalid task instance: " + currTask);
                }
                fw.write(saveText + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error occurred while saving your data. Please try again");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads all tasks in text file into a new TaskList instance
     *
     * @return the list of tasks from the saved text file
     * @throws DukeException if unable to parse file into TaskList
     */
    public ArrayList<Task> loadData() {
        TaskList taskList = new TaskList();
        try {
            File file = new File(FILE_PATH);
            Scanner readFile = new Scanner(file);
            int numTaskAdded = 0;

            while (readFile.hasNext()) {
                try {
                    String[] savedData = readFile.nextLine().split(" \\| ");
                    String command;
                    assert savedData.length > 0 : "Empty line in file!";

                    switch (savedData[0]) {
                    case "T":
                        command = "todo " + savedData[2];
                        taskList.addToDoTask(command);
                        break;
                    case "D":
                        command = "deadline " + savedData[2] + " /by " + savedData[3];
                        taskList.addDeadlineTask(command);
                        break;
                    case "E":
                        command = "event " + savedData[2] + " /at " + savedData[3];
                        taskList.addEventTask(command);
                        break;
                    default:
                        throw new DukeException("Unable to parse file: " + FILE_PATH);
                    }
                    if (savedData[1].equals("1")) {
                        taskList.completedTask(numTaskAdded);
                    }
                    numTaskAdded++;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Unable to parse line " + (numTaskAdded + 1) + " of " + FILE_PATH);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
            System.out.println("Loaded all tasks");
        } catch (IOException e) {
            System.out.println("Unable to load data from " + FILE_PATH);
        }
        return taskList.getTasks();
    }

    /**
     * Asserts that the paths specified have a value.
     */
    public static void checkValidPath() {
        assert Storage.HOME != null : "Home should be initialized";
        assert Storage.PATH != null : "Path should be initialized";
    }

    /**
     * Creates directory for the output file.
     */
    public static void createFolder() {
        Storage.checkValidPath();
        java.nio.file.Path path = java.nio.file.Paths.get(Storage.HOME, "desktop", "ip", "data");
        File file = new File(String.valueOf(path));
        file.mkdirs();
    }
}
