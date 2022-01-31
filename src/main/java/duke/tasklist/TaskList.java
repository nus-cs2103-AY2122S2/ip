package duke.tasklist;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents a TaskList that contains all added tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructor for a TaskList object.
     *
     * @param arr An ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> arr) {
        this.tasks = arr;
    }

    /**
     * Gets a task at a particular index.
     *
     * @param index The index of the task to return.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Gets the total number of tasks in the TaskList.
     *
     * @return The total number of tasks.
     */
    public int getCount() {
        return this.tasks.size();
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added to the TaskList.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes a task at a particular index.
     *
     * @param index The index of the task to remove.
     */
    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

    public void clearAllTask() {
        this.tasks.clear();
    }

}
