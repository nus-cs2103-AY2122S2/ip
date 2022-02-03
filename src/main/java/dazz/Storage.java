package dazz;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dazz.exception.InvalidDateFormatException;
import dazz.task.Deadline;
import dazz.task.Event;
import dazz.task.Task;
import dazz.task.Todo;

/**
 * Represents the backend of Dazz
 */
public class Storage {
    private File file;

    /**
     * Constructs a <code>Storage</code> object.
     * Directory and file will be created if not exists.
     */
    public Storage() {
        try {
            Path path = Paths.get("data/");
            if (!Files.exists(path)) {
                Files.createDirectory(path);
            }
            this.file = new File("data/tasks.txt");
            this.file.createNewFile();
        } catch (FileAlreadyExistsException e) {
            e.getStackTrace();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    /**
     * Loads the text file containing different <code>Task</code>.
     * @return The list of <code>Task</code> read.
     */
    public List<Task> loadList() {
        List<Task> tasks = null;
        try {
            Scanner scanner = new Scanner(this.file);
            tasks = new ArrayList<>();
            while (scanner.hasNext()) {
                tasks.add(parseInputFromTextFile(scanner.nextLine()));
            }
            return tasks;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return new ArrayList<>();
        } catch (InvalidDateFormatException e) {
            return tasks;
        }
    }

    private Task parseInputFromTextFile(String input) throws InvalidDateFormatException {
        String[] arr = input.split("===");
        String taskType = arr[0].trim();
        boolean isDone = arr[1].trim().equals("1");
        Task task = null;
        switch (taskType) {
        case "D":
            task = new Deadline(arr[2].trim(), isDone, Ui.toLocalDateTime(arr[3].trim()));
            break;
        case "E":
            task = new Event(arr[2].trim(), isDone, Ui.toLocalDateTime(arr[3].trim()));
            break;
        case "T":
            task = new Todo(arr[2].trim(), isDone);
            break;
        default:
            break;
        }
        return task;
    }

    /**
     * Updates the text file whenever there is a change to the <code>TaskList</code>.
     * @param taskList The <code>TaskList</code> that has changed.
     */
    public void updateList(TaskList taskList) {
        try {
            String filePath = file.getPath();
            FileWriter fw = new FileWriter(filePath);
            List<Task> list = taskList.getTasks();
            for (Task task : list) {
                fw.write(task.writeToFile() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

}
