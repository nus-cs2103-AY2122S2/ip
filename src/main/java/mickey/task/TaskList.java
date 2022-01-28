package mickey.task;

import java.util.ArrayList;
import java.util.List;

/**
 * TaskList to store tasks.
 */
public class TaskList {
    /** List of tasks. */
    protected List<Task> tasks;

    /**
     * Constructor.
     *
     * @param tasks List of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructor.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns number of tasks.
     *
     * @return Number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Gets task from index.
     *
     * @param index Index of task.
     * @return Task at index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Removes task from index.
     *
     * @param index Index of task
     * @return Removed task.
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Gets all tasks.
     *
     * @return List of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds task to list.
     *
     * @param t Task to add.
     */
    public void add(Task t) {
        tasks.add(t);
    }
}
