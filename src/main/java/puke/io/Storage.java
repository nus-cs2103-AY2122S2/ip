package puke.io;

import puke.exception.PukeException;
import puke.task.Deadline;
import puke.task.Event;
import puke.task.Task;
import puke.task.TaskList;
import puke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Handles the storage of tasks on file for saving between sessions.
 */
public class Storage {
    private final String filePath;
    private final File file;

    /**
     * Initialises the storage file.
     *
     * @param s Path of the storage file.
     */
    public Storage(String s) {
        filePath = s;
        file = new File(s);
    }

    /**
     * Loads the tasks from the file onto the tasks list.
     *
     * @param tasks List of tasks for the current session.
     * @throws PukeException If storage file/directory cannot be created.
     */
    public void loadTasks(TaskList tasks) throws PukeException {
        try {
            file.getParentFile().mkdir();
            file.createNewFile();
            Scanner fileSc = new Scanner(file);
            populateTasks(tasks, fileSc);
        } catch (IOException e) {
            throw new PukeException("Data file cannot be created!");
        }
    }

    /**
     * Populates the task list with tasks from the file.
     *
     * @param tasks List of tasks for the current session.
     * @param s Scanner loaded with the storage file.
     */
    private void populateTasks(TaskList tasks, Scanner s) {
        while (s.hasNext()) {
            String[] taskInfo = s.nextLine().split("@@");
            Task t = null;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            switch (taskInfo[0]) {
            case "T":
                t = new Todo(taskInfo[2]);
                break;
            case "D":
                t = new Deadline(taskInfo[2], LocalDateTime.parse(taskInfo[3], formatter));
                break;
            case "E":
                t = new Event(taskInfo[2], LocalDateTime.parse(taskInfo[3], formatter));
                break;
            }

            if (taskInfo[1].equals("1")) {
                t.mark();
            }

            tasks.addTaskToList(t);
        }
    }

    /**
     * Saves the tasks from the current session onto the file.
     *
     * @param tasks List of tasks from the current session.
     * @throws PukeException If the tasks cannot be saved to file.
     */
    public void saveTasks(TaskList tasks) throws PukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(tasks.generateStorageData());
            fw.close();
        } catch (IOException e) {
            throw new PukeException("Tasks cannot be saved to file");
        }

    }
}
