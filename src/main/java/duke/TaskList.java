package duke;

import java.util.ArrayList;

/**
 * TaskList class handles all the operations to do with creating and
 * updating the list of tasks
 */
public class TaskList {
    /**
     * Stores each individual Task object
     */
    private ArrayList<Task> tasks;

    /**
     * Constructor for TaskList.
     *
     * @param tasks the tasks to be stored
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task.
     *
     * @param t the task to be added
     */
    void add(Task t) {
        tasks.add(t);
    }

    /**
     * Returns the no of tasks currently in the list.
     *
     * @return the length of the task list
     */
    int size() {
        return tasks.size();
    }

    /**
     * Returns the no of tasks currently in the list.
     *
     * @return the length of the task list
     */
    Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Removes the task at that specific index in the list.
     *
     * @param index the position of the task to be deleted
     */
    void remove(int index) {
        tasks.remove(index);
    }
}
