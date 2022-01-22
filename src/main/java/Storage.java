import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// deals with loading tasks from the file and saving tasks in the file
public class Storage {
    public static final String DEFAULT_FILEPATH = "duke.txt";
    public final Path filePath;

    public Storage() {
        this(DEFAULT_FILEPATH);
    }

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    public void save(TaskList tasks) {
        try {
            List<String> encodedTaskList = encodeTaskList(tasks);
            Files.write(filePath, encodedTaskList);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public List<Task> load() {
        if (!checkIfFileExists(filePath)) {
            createFile(filePath);
            return new ArrayList<>();
        }

        try {
            return decodeTaskList(Files.readAllLines(filePath));
        } catch (IOException e) {
            System.out.println(e);
        }

        return new ArrayList<>();
    }

    private boolean checkIfFileExists(Path filePath) {
        return Files.exists(filePath) && Files.isRegularFile(filePath);
    }

    private void createFile(Path filePath) {
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
            System.out.println(e);
        }
    }

    public static List<String> encodeTaskList(TaskList tasks) {
        final List<String> encodedTasks = new ArrayList<>();
        tasks.getAllTasks().forEach(task -> encodedTasks.add(encodeTaskToString(task)));
        return encodedTasks;
    }

    private static String encodeTaskToString(Task task) {
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

    public static List<Task> decodeTaskList(List<String> encodedTaskList) {
        final List<Task> decodedTasks = new ArrayList<>();
        for (String encodedTask : encodedTaskList) {
            decodedTasks.add(decodeTaskFromString(encodedTask));
        }
        return decodedTasks;
    }

    private static Task decodeTaskFromString(String encodedTask) {
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
