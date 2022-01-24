package duke.storage;

import duke.command.Command;
import duke.taskList.TaskList;
import duke.exception.DukeException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Storage {
    private final TaskList tasks;

    public Storage(TaskList tasks) throws IOException {
        this.tasks = tasks;
        this.loadTask();
    }

    private void loadTask() throws IOException {
        try {
            Path path = Paths.get(".", "data", "duke.txt");
            if (Files.notExists(path)) {
                Files.createDirectories(Paths.get(".", "data"));
                Files.createFile(path);
            }
            List<String> list = Files.readAllLines(path);
            for (String s : list) {
                if (s.equals("")) {
                    continue;
                }
                if (s.charAt(0) == 'T') {
                    String[] temp = s.split(" ", 3);
                    tasks.addTask(Command.TODO, temp[2], false);
                    if (temp[1].charAt(1) == '1') {
                        tasks.markAsDone(String.valueOf(tasks.size()), false);
                    }
                } else if (s.charAt(0) == 'D') {
                    String[] temp = s.split(" ", 3);
                    tasks.addTask(Command.DEADLINE, temp[2], false);
                    if (temp[1].charAt(1) == '1') {
                        tasks.markAsDone(String.valueOf(tasks.size()), false);
                    }
                } else if (s.charAt(0) == 'E') {
                    String[] temp = s.split(" ", 3);
                    tasks.addTask(Command.EVENT, temp[2], false);
                    if (temp[1].charAt(1) == '1') {
                        tasks.markAsDone(String.valueOf(tasks.size()), false);
                    }
                }
            }
        } catch (DukeException ignored) {}
    }

    public void saveTask() throws IOException {
        Path path = Paths.get("data","duke.txt");
        Files.write(path, tasks.saveToFile());
    }
}
