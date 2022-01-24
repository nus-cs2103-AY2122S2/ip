package duke.function;

import java.util.ArrayList;
import java.util.List;

import duke.task.Task;

/**
 * Represents a collection of Tasks.
 */
public class TaskList {
    /**
     * A list of tasks.
     */
    List<Task> tasks;

    /**
     * Initializes the TaskList with an empty ArrayList of Tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Initializes the TaskList with the given List of Tasks.
     *
     * @param tasks tasks to be inserted into the TaskList.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns the number of tasks.
     *
     * @return The number of tasks.
     */
    public Integer getSize() {
        return this.tasks.size();
    }

    /**
     * Returns the task according to its index (1-based).
     *
     * @param number The index (1-based) of the task.
     * @return The task retrieved from its index (1-based).
     */
    public Task getByNumber(int number) {
        return this.tasks.get(number - 1);
    }

    /**
     * Deletes the task according to its index (1-based).
     *
     * @param number The index (1-based) of the task.
     * @return The task deleted from its index (1-based).
     */
    public Task deleteByNumber(int number) {
        return tasks.remove(number - 1);
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

}
