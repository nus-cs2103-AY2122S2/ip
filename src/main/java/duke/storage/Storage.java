package duke.storage;

import duke.exception.ErrorMessage;
import duke.exception.StorageException;
import duke.task.*;
import duke.util.TaskList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Storage class that deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    public static final String DEFAULT_FILEPATH = "duke.txt";
    private final Path filePath;

    public Storage() {
        this(DEFAULT_FILEPATH);
    }

    /**
     * Creates a new storage with the given file path.
     *
     * @param filePath Path of the file containing stored tasks.
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    /**
     * Saves the tasks in the list to a file.
     *
     * @param tasks List of tasks to be saved.
     * @throws StorageException if the tasks cannot be written to the file.
     */
    public void save(TaskList tasks) throws StorageException {
        try {
            List<String> encodedTaskList = encodeTaskList(tasks);
            Files.write(filePath, encodedTaskList);
        } catch (IOException e) {
            throw new StorageException(ErrorMessage.MESSAGE_FILE_SAVE_ERROR + filePath);
        }
    }

    /**
     * Loads the saved tasks from file if they exist.
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
            return decodeTaskList(Files.readAllLines(filePath));
        } catch (IOException ioe) {
            throw new StorageException(ErrorMessage.MESSAGE_FILE_LOAD_ERROR + filePath);
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

    private List<String> encodeTaskList(TaskList tasks) {
        final List<String> encodedTasks = new ArrayList<>();
        tasks.getAllTasks().forEach(task -> encodedTasks.add(encodeTaskToString(task)));
        return encodedTasks;
    }

    private String encodeTaskToString(Task task) {
        final StringBuilder encodedTaskBuilder = new StringBuilder();
        encodedTaskBuilder.append(task.getTypeAsPrefix()).append(" | ");
        encodedTaskBuilder.append(task.getIsDone() ? 1 : 0).append(" | ");
        encodedTaskBuilder.append(task.getDescription());
        switch (task.getType()) {
        case DEADLINE:
            Deadline deadlineTask = (Deadline) task;
            encodedTaskBuilder.append(" | ").append(deadlineTask.getBy().toString());
            break;
        case EVENT:
            Event eventTask = (Event) task;
            encodedTaskBuilder.append(" | ").append(eventTask.getAt().toString());
            break;
        }
        return encodedTaskBuilder.toString();
    }

    private ArrayList<Task> decodeTaskList(List<String> encodedTaskList) {
        final ArrayList<Task> decodedTasks = new ArrayList<>();
        for (String encodedTask : encodedTaskList) {
            decodedTasks.add(decodeTaskFromString(encodedTask));
        }
        return decodedTasks;
    }

    private Task decodeTaskFromString(String encodedTask) {
        String[] args = encodedTask.split(" \\| ");
        switch(args[0]) {
        case "D":
            return new Deadline(args[2], args[1].equals("1"), LocalDate.parse(args[3]));
        case "E":
            return new Event(args[2], args[1].equals("1"), LocalDate.parse(args[3]));
        default:
            return new Todo(args[2], args[1].equals("1"));
        }
    }
}
