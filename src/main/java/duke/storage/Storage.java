package duke.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import duke.command.Command;
import duke.exception.DukeException;
import duke.tasklist.TaskList;

/**
 * Represents the hard disk that stores the tasks between sessions.
 */
public class Storage {
    private final TaskList tasks;

    /**
     * Returns a Storage Object and loads the task in the current file.
     *
     * @param tasks TaskList to be used.
     * @throws IOException If there was an error of I/O while loading the tasks.
     */
    public Storage(TaskList tasks) throws IOException {
        this.tasks = tasks;
        this.loadTask();
    }

    /**
     * Loads the tasks from file.
     *
     * @throws IOException If there was an error of I/O while loading the tasks.
     */
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
        } catch (DukeException ignored) {
            //meant to be empty
        }
    }

    /**
     * Saves the tasks in TaskList into the file.
     *
     * @throws IOException If there was an error of I/O while saving the tasks.
     */
    public void saveTask() throws IOException {
        Path path = Paths.get("data", "duke.txt");
        Files.write(path, tasks.saveToFile());
    }
}
