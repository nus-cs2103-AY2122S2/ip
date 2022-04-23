package seedu.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import seedu.duke.DukeException;
import seedu.task.Deadline;
import seedu.task.Event;
import seedu.task.Task;
import seedu.task.Todo;

/**
 * The Storage class
 */
public class Storage {

    private File file;
    private String path;

    /**
     * Constructor
     *
     * @param filePath file path of the save file
     */
    public Storage(String filePath) {
        file = Paths.get(filePath).toFile();
        path = Paths.get(filePath).toAbsolutePath().toString();
    }

    /**
     * Returns the file path of the save file
     *
     * @return The file path of the save file
     */
    public String getPath() {
        return path;
    }

    /**
     * Parses the save file to be used in the task list
     *
     * @return A list of tasks parsed from the save file
     * @throws DukeException Cannot load save file into the task list
     */
    public ArrayList<Task> load() throws DukeException {

        Paths.get(file.getParent()).toFile().mkdirs();
        createFile();

        try {
            Scanner sc = new Scanner(file);
            ArrayList<Task> tasks = new ArrayList<>();

            while (sc.hasNextLine()) {
                String[] task = sc.nextLine().split("\t");
                boolean isCompleted = Boolean.parseBoolean(task[2]);
                int priority = Integer.parseInt(task[3]);

                switch (task[0]) {
                case "T":
                    tasks.add(new Todo(task[1], isCompleted, priority));
                    break;
                case "D":
                    assert task.length == 5 : "Task saved incorrectly";
                    LocalDateTime byDate = LocalDateTime.parse(task[task.length - 1], Task.getFormatter());
                    tasks.add(new Deadline(task[1], isCompleted, byDate, priority));
                    break;
                case "E":
                    assert task.length == 5 : "Task saved incorrectly";
                    LocalDateTime atDate = LocalDateTime.parse(task[task.length - 1], Task.getFormatter());
                    tasks.add(new Event(task[1], isCompleted, atDate, priority));
                    break;
                default:
                    throw new DukeException("Unknown task type found: " + task[0]);
                }
            }

            return tasks;
        } catch (FileNotFoundException e) {
            throw new DukeException("Save file cannot be found.");
        }
    }

    /**
     * Creates the save file
     * Does nothing if save file already exists
     *
     * @throws DukeException Cannot create save file
     */
    private void createFile() throws DukeException {
        try {
            file.createNewFile();
            assert file.exists() : "No save file exists at all";
        } catch (IOException e) {
            throw new DukeException("File creation error. So, cannot save.");
        }
    }

    /**
     * Saves the entire list into the save file
     *
     * @param tasks The task list to be saved
     * @throws DukeException Cannot save into the save file
     */
    public void saveAll(ArrayList<Task> tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(file);

            for (Task t : tasks) {
                fw.write(t.toFile() + "\n");
            }

            fw.close();
        } catch (IOException e) {
            throw new DukeException("Save error occurred. Cannot save.");
        }
    }
}
