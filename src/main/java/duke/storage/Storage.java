package duke.storage;

import duke.exception.ErrorMessage;
import duke.exception.StorageException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.util.TaskList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a storage object that deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    public static final String DEFAULT_FILEPATH = "duke.txt";
    private final Path filePath;

    public Storage() {
        this(DEFAULT_FILEPATH);
    }

    /**
     * Creates a new storage with the specified file path.
     *
     * @param filePath Path of the file containing the stored tasks.
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    /**
     * Loads and decodes the saved tasks from file if they exist.
     *
     * @return Saved tasks, else empty list.
     * @throws StorageException if the file cannot be loaded.
     */
    public ArrayList<Task> load() throws StorageException {
        if (!checkIfFileExists(filePath)) {
            createFile(filePath);
            return new ArrayList<>();
        }

        try {
            List<String> lines = Files.readAllLines(filePath);
            ArrayList<Task> tasks = new ArrayList<>();
            for (String line : lines) {
                String[] args = line.split(" \\| ");
                switch(args[0]) {
                case "D":
                    tasks.add(new Deadline(args[2], args[1].equals("1"), LocalDate.parse(args[3])));
                    // Fallthrough
                case "E":
                    tasks.add(new Event(args[2], args[1].equals("1"), LocalDate.parse(args[3])));
                    // Fallthrough
                default:
                    tasks.add(new Todo(args[2], args[1].equals("1")));
                }
            }
            return tasks;
        } catch (IOException ioe) {
            throw new StorageException(ErrorMessage.MESSAGE_FILE_LOAD_ERROR + filePath);
        }
    }

    /**
     * Saves the tasks in the list to a file.
     *
     * @param tasks List of tasks to be saved.
     * @throws StorageException if the tasks cannot be written to the file.
     */
    public void save(TaskList tasks) throws StorageException {
        try {
            StringBuilder content = new StringBuilder();
            for (Task t: tasks.getAllTasks()) {
                content.append(t.formatForFile());
            }
            Files.write(filePath, content.toString().getBytes());
        } catch (IOException e) {
            throw new StorageException(ErrorMessage.MESSAGE_FILE_SAVE_ERROR + filePath);
        }
    }

    private boolean checkIfFileExists(Path filePath) {
        return Files.exists(filePath) && Files.isRegularFile(filePath);
    }

    private void createFile(Path filePath) throws StorageException {
        try {
            if (checkIfFileExists(filePath)) {
                return;
            }

            Path parentDir = filePath.getParent();
            if (!Files.exists(parentDir)) { // check if data folder exists
                Files.createDirectories(parentDir);
            }

            Files.createFile(filePath);
        } catch (IOException e) {
            throw new StorageException(ErrorMessage.MESSAGE_FILE_SAVE_ERROR + filePath);
        }
    }
}
