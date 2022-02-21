package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Event;
import duke.task.Deadline;
import duke.task.TaskList;

/**
 * Represents a storage space for tasks on hard-drive.
 * It handles the loading of tasks from a file and saving
 * of tasks to the same file.
 *
 * @author  Elumalai Oviya Dharshini
 * @version 1.0
 */
public class Storage {
    private String filePath;
    private ArrayList<Task> tasks;

    /**
     * Suppresses any unused warnings from a given boolean result.
     *
     * @param result variable to suppress warnings from
     */
    //@@author Eric Lange-reused
    //Reused from https://stackoverflow.com/questions/27904329/warning-file-mkdir-is-ignored#answer-54341862
    // with minor modifications
    @SuppressWarnings("unused")
    private static void ignoreResult(boolean result) {

    }
    //@@author

    /**
     * Constructor for Storage.
     * Initializes the Storage object with a given file path.
     * It parses input from the specified file and saves the list of Tasks from
     * the specified file, if any. If file does not exist, it creates the file.
     * Note: any missing parent directories in the specified file path are
     * created prior to file creation.
     *
     * @param filePath path of the specified file from the current directory
     * @throws IOException if an exception occurs in the creation/access of
     * the specified file
     * @throws RuntimeException if file at specified path contains data in a
     * non-standard format
     */
    Storage(String filePath) throws IOException {
        tasks = new ArrayList<>();
        this.filePath = filePath;
        File data = new File(filePath);
        //make preceding directories, if any are not found
        ignoreResult(data.getParentFile().mkdirs());

        // If file does not exist, create new file and return
        if (data.createNewFile()) {
            return;
        }

        Scanner fileReader = new Scanner(data);
        while (fileReader.hasNextLine()) {
            String line = fileReader.nextLine();
            String[] tmp = line.split("\\|");
            boolean isDone = tmp[1].trim().equals("D");

            switch (tmp[0].trim()) {
            case "T":
                todoHandler(tmp, isDone);
                break;
            case "D":
                deadlineHandler(tmp, isDone);
                break;
            case "E":
                eventHandler(tmp, isDone);
                break;
            default:
                throw new RuntimeException("Corrupted data in data file at "
                        + filePath);
            }
        }
        System.out.println("start" + tasks);
        fileReader.close();
    }

    /**
     * Adds Todo to tasks.
     *
     * @param arr array of data [Type, isDone, description]
     * @param isDone bool indicating if Todo is marked complete
     */
    public void todoHandler(String[] arr, Boolean isDone) {
        Todo t = new Todo(arr[2].trim());
        if (isDone) {
            t.markComplete();
        }
        tasks.add(t);
    }

    /**
     * Adds Deadline to tasks.
     *
     * @param arr array of data [Type, isDone, description, dateTime]
     * @param isDone bool indicating if Deadline is marked complete
     */
    public void deadlineHandler(String[] arr, Boolean isDone) {
        Deadline d = new Deadline(arr[2].trim(), arr[3].trim());
        if (isDone) {
            d.markComplete();
        }
        tasks.add(d);
    }

    /**
     * Adds Event to tasks.
     *
     * @param arr array of data [Type, isDone, description, dateTime]
     * @param isDone bool indicating if Deadline is marked complete
     */
    public void eventHandler(String[] arr, Boolean isDone) {
        Event d = new Event(arr[2].trim(), arr[3].trim());
        if (isDone) {
            d.markComplete();
        }
        tasks.add(d);
    }

    /**
     * Retrieves and returns Tasks saved from the storage file.
     *
     * @return ArrayList of saved Tasks
     */
    public ArrayList<Task> load() {
        return tasks;
    }

    /**
     * Updates the list of Tasks saved on hard-drive at filePath.
     * This overwrites the existing file at filePath.
     * Note: File creation is to be handled via load() prior this method as
     * this assumes that filePath is valid and that the file exists at filePath.
     *
     * @param tasks TaskList of Tasks to be saved on hard-drive
     * @throws IOException if an exception occurs in the saving of
     * data to the file at filePath
     */
    public void save(TaskList tasks) throws IOException {
        File data = new File(filePath);
        FileWriter f;

        f = new FileWriter(data, false);
        boolean isFirst = true;
        for (int i = 0; i < tasks.size(); i++) {
            String s = isFirst ? "" : "\n";
            f.write(s + tasks.get(i).writeToFile());
            isFirst = false;
        }
        f.close();
        System.out.println("saved" +tasks);
    }
}
