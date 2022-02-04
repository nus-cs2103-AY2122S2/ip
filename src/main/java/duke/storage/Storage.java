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
            createPathIfNotExist(path);

            List<String> list = Files.readAllLines(path);

            for (String s : list) {
                if (s.equals("")) {
                    continue;
                }

                if (s.charAt(0) == 'T') {
                    addToList(s, Command.TODO);
                } else if (s.charAt(0) == 'D') {
                    addToList(s, Command.DEADLINE);
                } else if (s.charAt(0) == 'E') {
                    addToList(s, Command.EVENT);
                }
            }
        } catch (DukeException ignored) {
            //meant to be empty
        }
    }

    private void createPathIfNotExist(Path path) throws IOException {
        if (Files.notExists(path)) {
            Files.createDirectories(Paths.get(".", "data"));
            Files.createFile(path);
        }
    }

    private void addToList(String task, Command command) throws DukeException {
        String[] temp = task.split(" ", 3);
        tasks.addTask(command, temp[2], false);
        if (temp[1].charAt(1) == '1') {
            tasks.markAsDone(String.valueOf(tasks.size()), false);
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
