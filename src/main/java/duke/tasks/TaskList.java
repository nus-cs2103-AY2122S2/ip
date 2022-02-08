package duke.tasks;

import java.util.ArrayList;
import java.util.List;

/**
 * List of tasks of this bot that the user has access to.
 */
public class TaskList {
    private final ArrayList<Task> listOfTasks;

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
        assert task != null;
        this.listOfTasks.add(task);
    }

    /**
     * Deletes the intended task from the list.
     *
     * @param n the index of the task in list to be deleted.
     */
    public void deleteTask(int n) {
        assert n >= 0;
        this.listOfTasks.remove(n);
    }

    /**
     * Mark the task as completed.
     *
     * @param n the index of the task in list to be marked as completed
     */
    public void markTask(int n) {
        assert n >= 0;
        this.getTask(n).mark();
    }

    /**
     * Unmark the completed task as not completed.
     *
     * @param n the index of the task in list to be marked as uncompleted
     */
    public void unMarkTask(int n) {
        assert n >= 0;
        this.getTask(n).unMark();
    }

    /**
     * Retrieve the task from the list.
     *
     * @param n the index of the task in list to be retrieved
     * @return the called task
     */
    public Task getTask(int n) {
        assert n >= 0;
        return this.listOfTasks.get(n);
    }

    /**
     * Get the number of tasks in the list.
     *
     * @return size of list
     */
    public int getSize() {
        return this.listOfTasks.size();
    }

    /**
     * Convert the arraylist into a list in order to use List calls
     *
     * @return a List of tasks in the collection.
     */
    public List<Task> taskListToList() {
        return listOfTasks;
    }
}
