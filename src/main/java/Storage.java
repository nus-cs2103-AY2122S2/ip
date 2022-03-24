import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Storage {
    private final Path path;

    Storage(String filePath) {
        this.path = Path.of(filePath);
    }

    public void save(TaskList taskList) throws IOException {
        String encodedTaskList = encodeTaskList(taskList);
        Files.writeString(path, encodedTaskList);
    }

    public TaskList load() throws IOException {
        if (Files.notExists(path)) {
            return new TaskList();
        } else {
            List<String> encodedTaskList = Files.readAllLines(path);
            return decodeTaskList(encodedTaskList);
        }
    }

    private String encodeTaskList(TaskList toSave) {
        StringBuilder encodedTaskList = new StringBuilder();
        for (Task task : toSave.getAllTask()) {
            encodedTaskList.append(task.toFileFormat());
        }
        return encodedTaskList.toString();
    }

    private TaskList decodeTaskList(List<String> encodedTaskList) throws IOException {
        TaskList decodedTaskList = new TaskList();
        for (String encodedTask : encodedTaskList) {
            String[] command = encodedTask.split(" \\| ");
            switch(command[0]) {
            case "T":
                decodedTaskList.addTaskToTaskList(new Todo(command[1], command[2]));
                break;
            case "D":
                decodedTaskList.addTaskToTaskList(new Deadline(command[1], command[2], command[3]));
                break;
            case "E":
                decodedTaskList.addTaskToTaskList(new Event(command[1], command[2], command[3]));
                break;
            }
        }
        return decodedTaskList;
    }
}
