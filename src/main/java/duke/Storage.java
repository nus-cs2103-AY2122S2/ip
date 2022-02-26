package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Handles hard disk storage for task list.
 */

public class Storage {

    private Path directory;
    private Path filePath;

    /**
     * Constructs storage object for task list.
     *
     * @param directory Directory file is in.
     * @param filePath Full path to file including file name.
     */
    public Storage(Path directory, Path filePath) {
        this.directory = directory;
        this.filePath = filePath;
    }

    /**
     * Loads list of tasks from file.
     *
     * @return List of tasks.
     * @throws DukeException If file not found or file data corrupted.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            File dir = directory.toFile();
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = filePath.toFile();
            file.createNewFile();
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String taskString = fileScanner.nextLine();
                Task task = constructTask(taskString);
                tasks.add(task);
            }
            fileScanner.close();
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
        return tasks;
    }

    /**
     * Constructs Task given data string.
     *
     * @return Task object.
     * @throws DukeException If string is not in correct format.
     */
    public Task constructTask(String taskString) throws DukeException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String[] data = taskString.split(",");
        String type = data[0];
        Boolean status = Boolean.parseBoolean(data[1]);
        String text = data[2];

        if (type.equals("T")) {
            return new Todo(text, status);
        } else if (type.equals("D")) {
            LocalDateTime date = LocalDateTime.parse(data[3], dateFormatter);
            return new Deadline(text, status, date);
        } else if (type.equals("E")) {
            LocalDateTime date = LocalDateTime.parse(data[3], dateFormatter);
            return new Event(text, status, date);
        } else {
            throw new DukeException("Cannot parse task list file data.");
        }
    }

    /**
     * Writes task list to file.
     *
     * @throws DukeException If couldn't write to file.
     */
    public void save(TaskList tasks) throws DukeException {
        try {
            File file = filePath.toFile();
            file.createNewFile();
            FileWriter myWriter = new FileWriter(file);
            myWriter.write(tasks.toDataString());
            myWriter.close();
        } catch (IOException e) {
            throw new DukeException("Couldn't write to file");
        }
    }

}
