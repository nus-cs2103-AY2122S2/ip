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
 * Long term storage for the data in the task list
 */
public class Storage {

    private final File FILE;

    public Storage(String filePath) {
        FILE = Paths.get(filePath).toFile();
    }

    /**
     * Reads contents of save file and initialises list.
     *
     * @return Arraylist of tasks read from save file.
     * @throws DukeException Throws any error occurred during reading process.
     */
    public ArrayList<Task> load() throws DukeException {

        if (Paths.get(FILE.getParent()).toFile().mkdirs()) {
            System.out.println("Info: Parent directory created.");
        } else {
            System.out.println("Info: Parent directory found.");
        }

        if (createFile()) {
            System.out.println("Info: " + FILE.getAbsolutePath() + " created.\n");
        } else {
            System.out.println("Info: Save file found.\n");
        }

        assert FILE.exists(): "No save file exists at all";

        try {
            Scanner sc = new Scanner(FILE);
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
                    assert task.length == 5: "Task saved incorrectly";
                    LocalDateTime byDate = LocalDateTime.parse(task[task.length-1], Task.getFormatter());
                    tasks.add(new Deadline(task[1], isCompleted, byDate, priority));
                    break;
                case "E":
                    assert task.length == 5: "Task saved incorrectly";
                    LocalDateTime atDate = LocalDateTime.parse(task[task.length-1], Task.getFormatter());
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

    private boolean createFile() throws DukeException {
        try {
            return FILE.createNewFile();
        } catch (IOException e) {
            throw new DukeException("File creation error. So, cannot save.");
        }
    }

    /**
     * Saves entire list into the save file.
     *
     * @param tasks Arraylist of tasks to be saved
     * @throws DukeException Throws error occurred when saving
     */
    public void saveAll(ArrayList<Task> tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(FILE);

            for (Task t : tasks) {
                fw.write(t.toFile() + "\n");
            }

            fw.close();
        } catch (IOException e) {
            throw new DukeException("Save error occurred. Cannot save.");
        }
    }
}
