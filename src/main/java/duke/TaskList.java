package duke;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Task list.
 */
public class TaskList {

    ArrayList<Task> tasks;
    public int items;
    public Path path;

    /**
     * Instantiates a new Task list.
     *
     * @param t      the list of tasks
     * @param number the number
     * @param path   the path
     */
    public TaskList(ArrayList<Task> t, int number, Path path) {
        tasks = t;
        items = number;
        this.path = path;
    }

    /**
     * Instantiates a new Task list.
     */
    public TaskList() {
        tasks = new ArrayList<Task>();
        path = null;
    }

    /**
     * Add new task to the list.
     *
     * @param task the task
     */
    public void add(Task task) {
        this.tasks.add(task);
        items++;
    }

    /**
     * Gets number of tasks.
     *
     * @return the number of tasks
     */
    public int getNumberOfTasks() {
        return items;
    }

    /**
     * Remove task from the list.
     *
     * @param n the index of task to be removed.
     */
    public void remove(int n) {
        items--;
        this.tasks.remove(n);
    }

    /**
     * Get task.
     *
     * @param n the index of task
     * @return the task indicated by the index
     */
    public Task get(int n) {
        return this.tasks.get(n);
    }

    /**
     * Set the task on the list.
     *
     * @param n the index of task to be set
     * @param t the task to be updated
     */
    public void set(int n, Task t) {
        this.tasks.set(n, t);
    }

    /**
     * Method to update the task in the file
     * if changes were made by the user.
     *
     * @param lineNumber The line in the file to be updated.
     * @param data       New string to replace the old one.
     * @throws IOException the io exception
     */
    public void updateTask(int lineNumber, String data) throws IOException {
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        lines.set(lineNumber - 1, data);
        Files.write(path, lines, StandardCharsets.UTF_8);
    }

    /**
     * Method to delete a line in the file.
     *
     * @param lineNumber The line in the file to be deleted.
     * @throws IOException the io exception
     */
    public  void deleteTask(int lineNumber) throws IOException {
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        lines.remove(lineNumber - 1);
        Files.write(path, lines, StandardCharsets.UTF_8);
    }
}
