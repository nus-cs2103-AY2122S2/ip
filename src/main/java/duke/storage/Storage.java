package duke.storage;

import duke.tasklist.TaskList;
import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private final String filePath;

    public Storage(String input) {
        this.filePath = input;
    }

    /**
     * Reads existing task text file from local folder
     * If file does not exist, create new text file
     */
    public void readFile() {
        try {
            Path dataFilePath = Paths.get("data/");
            Files.createDirectories(dataFilePath);
            File file = new File(this.filePath);

            if (file.createNewFile()) {
                System.out.println("New file created at " + filePath);
            }
        } catch (IOException e) {
            System.out.println("IO Exception error occurred, unable to create file " + filePath);
        }
    }

    /**
     * Save current task list into a text file
     *
     * @param tasks the list of tasks
     */
    public void saveData(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(filePath);

            for (Task currTask : tasks.getTasks()) {
                String description = currTask.getDescription();
                int isComplete = currTask.isComplete() ? 1 : 0;
                String saveText;

                if (currTask instanceof ToDo) {
                    saveText = "T | " + isComplete + " | " + description;
                } else if (currTask instanceof Deadline) {
                    saveText = "D | " + isComplete + " | " + description + " | " + ((Deadline) currTask).getDeadlineBy();
                } else if (currTask instanceof Event) {
                    saveText = "E | " + isComplete + " | " + description + " | " + ((Event) currTask).getEventBy();
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
     */
    public ArrayList<Task> loadData() {
        TaskList taskList = new TaskList();
        try {
            File file = new File(filePath);
            Scanner readFile = new Scanner(file);
            int numTaskAdded = 0;

            while (readFile.hasNext()) {
                try {
                    String[] savedData = readFile.nextLine().split(" \\| ");
                    String command;
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
                            throw new DukeException("Unable to parse file: " + filePath);
                    }
                    if (savedData[1].equals("1")) {
                        taskList.completedTask(numTaskAdded);
                    }
                    numTaskAdded++;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Unable to parse line " + (numTaskAdded + 1) + " of " + filePath);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("IO exception error when loading data " + filePath);
        }
        return taskList.getTasks();
    }
}
