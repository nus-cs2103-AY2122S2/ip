package duke.tasks;

import duke.tasks.Task;

import java.util.ArrayList;

/**
 * List of tasks of this bot that the user has access to.
 */
public class TaskList {
    private ArrayList<Task> listOfTasks;

    /**
     * Constructor of this TaskList class.
     * Creates a new empty list.
     */
    public TaskList() {
        this.listOfTasks = new ArrayList<>();
    }

    /**
     * Constructor of this TaskList class.
     * Creates a list with the existing tasks.
     *
     * @param listOfTasks the tasks read from the file provided
     */
    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    /**
     * Adds a task into this list.
     *
     * @param task a Task type to be added into list
     */
    public void addTask(Task task) {
        this.listOfTasks.add(task);
    }

    /**
     * Deletes the intended task from the list.
     *
     * @param n the index of the task in list to be deleted.
     */
    public void deleteTask(int n) {
        this.listOfTasks.remove(n);
    }

    /**
     * Mark the task as completed.
     *
     * @param n the index of the task in list to be marked as completed
     */
    public void markTask(int n) {
        this.getTask(n).mark();
    }

    /**
     * Unmark the completed task as not completed.
     *
     * @param n the index of the task in list to be marked as uncompleted
     */
    public void unMarkTask(int n) {
        this.getTask(n).unMark();
    }

    /**
     * Retrieve the task from the list.
     *
     * @param n the index of the task in list to be retrieved
     * @return the called task
     */
    public Task getTask(int n) {
        return this.listOfTasks.get(n);
    }

    /**
     * Get size of list.
     *
     * @return size of list
     */
    public int getSize() {
        return this.listOfTasks.size();
    }
}
