package duke.storage;

import static duke.commons.core.LogMessages.LOG_BLANK_FILE_CREATE_FAIL;
import static duke.commons.core.LogMessages.LOG_FILE_NOT_FOUND;
import static duke.commons.core.LogMessages.LOG_SAVE_FILE_FAIL;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Handles all data file Storage operations
 */
public class Storage {
    private static Path filePath = Paths.get("./data/data.txt");
    private Path dirPath = Paths.get("./data/");
    private ArrayList<Task> tasks;

    /**
     * Default constructor
     */
    public Storage() {
        this.tasks = new ArrayList<>();
    }

    /**
     * @return list of task objects read from file
     */
    public List<Task> loadTasksFromFile() {

        if (!Files.exists(dirPath)) {
            createBlankFile(dirPath, filePath);
        } else {
            List<String> fileData = readFile();
            convertStringToTasks(fileData);
        }

        return this.tasks;
    }

    /**
     * @param tasks list of task objects to be saved to disk
     */
    public void saveTasksToFile(List<Task> tasks) {
        try {
            Files.deleteIfExists(filePath);
            createBlankFile(dirPath, filePath);
            for (Task t : tasks) {
                Files.write(filePath, (t.getPrintString() + System.lineSeparator()).getBytes(),
                        StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            System.out.println(LOG_SAVE_FILE_FAIL);
        }
    }

    private void createBlankFile(Path dirPath, Path filePath) {
        try {
            Files.createDirectories(dirPath);
            Files.createFile(filePath);
        } catch (IOException e) {
            System.out.println(LOG_BLANK_FILE_CREATE_FAIL);
        }
    }

    /**
     * @return list of tasks in raw string format that are read from file
     */
    private List<String> readFile() {
        try {
            return Files.readAllLines(filePath);
        } catch (IOException e) {
            System.out.println(LOG_FILE_NOT_FOUND);
            return null;
        }
    }

    /**
     * @param fileData list of tasks in String format to be converted to List of Tasks
     */
    public void convertStringToTasks(List<String> fileData) {
        if (fileData != null) {
            for (String line : fileData) {
                String[] item = line.split("\\|");
                switch (item[0].replaceAll("\\s", "")) {
                case "D":
                    this.tasks.add(new Deadline(item[1].replaceAll("\\s", "").equals("1"), item[2], item[3]));
                    break;
                case "E":
                    tasks.add(new Event(item[1].replaceAll("\\s", "").equals("1"), item[2], item[3]));
                    break;
                default:
                    tasks.add(new Todo(item[1].replaceAll("\\s", "").equals("1"), item[2]));
                }
            }
        }
    }

    /**
     * Prints the current working directory of this running instance of the program
     */
    public void printCurrentDirectory() {
        File directory = new File("./");
        System.out.println(directory.getAbsolutePath());
    }

}
