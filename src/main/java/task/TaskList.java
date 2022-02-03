package task;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Implements Serializable package to
 * store the user's task into a file
 * which facilitates the caching feature.
 */
public class TaskList implements Serializable {
    protected ArrayList<Task> tasks;

    /**
     * Default class constructor.
     * Instantiates a new ArrayList instance.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Class constructor.
     * Associates class' ArrayList of task to the params'.
     *
     * @param tasks ArrayList of task
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets a list of user's tasks.
     *
     * @return ArrayList of tasks
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Removes a task from the list of tasks.
     * Operates only if the index is in range of the ArrayList.
     *
     * @param index Position of ArrayList to remove the task
     */
    public void removeTask(int index) {
        if (index >= 0 && index <= tasks.size()) {
            tasks.remove(index);
        }
    }

    /**
     * Gets the ArrayList task's size.
     *
     * @return ArrayList task's size
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Gets a specific task from the list of user's tasks.
     *
     * @param index Position of ArrayList to retrieve from
     * @return User's task
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Adds a new task into the ArrayList tasks.
     *
     * @param task User's task
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Searches for the task(s) that matches the search term.
     *
     * @param search Search term of the task's description
     * @return An ArrayList of tasks that matches the search term
     */
    public ArrayList<Task> find (String search) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getDescription() != null && t.getDescription().toLowerCase().contains(search)) {
                foundTasks.add(t);
            }
        }
        return foundTasks;
    }
}

