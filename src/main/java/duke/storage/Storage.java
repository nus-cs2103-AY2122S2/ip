package duke.storage;

import java.util.ArrayList;
import java.util.List;

import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Storage {
    private ArrayList<Task> tasks;

    Path dirPath = Paths.get("../data/");
    static Path filePath = Paths.get("../data/data.txt");

    /**
     * Default constructor
     */
    public Storage() {
        this.tasks = new ArrayList<>();
    }

    /**
     * @return List<Task> list of task objects read from file
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
            e.printStackTrace();
        }
    }

    private void createBlankFile(Path dirPath, Path filePath) {
        try {
            Files.createDirectories(dirPath);
            Files.createFile(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return List<String> list of tasks in raw string format that are read from file
     */
    private List<String> readFile() {
        try {
            return Files.readAllLines(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param fileData list of tasks in String format to be converted to List<Task>
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