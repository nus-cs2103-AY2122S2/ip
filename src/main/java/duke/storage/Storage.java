package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import duke.constants.Constants;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Handles storage reads and writes.
 */
public class Storage {
    private String filePath;

    /**
     * Creates a Storage object that allows program to perform storage
     * reads and writes throughout lifetime of program.
     * 
     * @param filePath filePath is the relative path to the text file that stores user's tasks.
     */
    public Storage(String filePath) {
        assert filePath != null : "Storage[Storage] filePath cannot be null.";
        assert filePath.length() > 0 : "Storage[Storage] filePath must contain data.";

        this.filePath = filePath;
    }

    /**
     * Loads tasks from local disk file into program tasks list.
     * 
     * @return List of tasks.
     * @throws IOException If error reading from local disk text file.
     */
    public ArrayList<Task> loadFromFile() throws IOException {
        Scanner sc;
        File directory = new File(Constants.FILE_PATH);
        ArrayList<Task> allTasks = new ArrayList<>();

        if (!directory.exists()) {
            directory.mkdir();
        }

        File file = new File(this.filePath);

        if (!file.exists()) {
            file.createNewFile();
        }

        sc = new Scanner(file);

        while (sc.hasNext()) {
            String task = sc.nextLine();
            String[] taskData = task.split("\\s\\|\\s");
            Boolean completed = Integer.parseInt(taskData[1]) == 1;

            if (task.charAt(0) == 'T') {
                Todo todo = new Todo(taskData[2]);

                todo.setCompleted(completed);
                allTasks.add(todo);
            } else if (task.charAt(0) == 'D') {
                Deadline deadline = new Deadline(
                        taskData[2], LocalDate.parse(taskData[3]));

                deadline.setCompleted(completed);
                allTasks.add(deadline);
            } else if (task.charAt(0) == 'E') {
                Event event = new Event(
                        taskData[2], LocalDate.parse(taskData[3]));

                event.setCompleted(completed);
                allTasks.add(event);
            }
        }

        sc.close();

        return allTasks;
    }

    /**
     * Writes to local disk text file.
     * 
     * @param taskString taskString is the task to be written.
     * @throws IOException If error writing to local disk text file.
     */
    public void writeToFile(String taskString) throws IOException {
        assert taskString != null : "Storage[writeToFile] taskString cannot be null.";
        assert taskString.length() > 0 : "Storage[writeToFile] taskString must contain data.";

        FileWriter fw = new FileWriter(this.filePath, true);

        fw.write(taskString);
        fw.write(System.getProperty("line.separator"));
        fw.close();
    }

    /**
     * Writes to local disk text file. Used for updating and deleting task.
     * 
     * @param taskString taskString is the task to be written.
     * @param index index is the task number of this transaction.
     * @param isDelete isDelete is an indicator of updating or deleting of task.
     * @throws IOException If error writing to local disk text file.
     */
    public void writeToFile(String taskString, int index, boolean isDelete) throws IOException {
        assert taskString != null : "Storage[writeToFile] taskString cannot be null.";
        assert taskString.length() > 0 : "Storage[writeToFile] taskString must contain data.";
        assert index > -1 : "Storage[writeToFile] index must be more than -1.";

        Path filePath = Paths.get(Constants.FILE_PATH, Constants.FILE_NAME);
        ArrayList<String> fileContent = new ArrayList<>(
                Files.readAllLines(filePath, StandardCharsets.UTF_8));

        if (isDelete) {
            fileContent.remove(index);
        } else {
            fileContent.set(index, taskString);
        }

        Files.write(filePath, fileContent);
    }
}
