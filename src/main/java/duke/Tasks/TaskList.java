package duke.Tasks;

import java.util.ArrayList;

/**
 * Stores the task list and contains the basic functionalities to modify/interact with the tasklist.
 */
public class TaskList {
    private final ArrayList<Task> listOfTasks;

    /**
     * Constructor for initialising a stored tasklist.
     *
     * @param listOfTasks Stored list of task
     */
    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    /**
     * Constructor for new tasklist.
     */
    public TaskList() {
        this.listOfTasks = new ArrayList<>();
    }

    /**
     * Returns the size of tasklist.
     *
     * @return the number of tasks in the list
     */
    public int size() {
        return this.listOfTasks.size();
    }

    /**
     * Gets a task from the list.
     *
     * @param index the index number of the task in the list
     * @return The task at the index
     */
    public Task get(int index) {
        return this.listOfTasks.get(index);
    }

    /**
     * Removes a task from the list.
     *
     * @param index the index number of the task in the list
     * @return The removed task
     */
    public Task remove(int index) {
        return this.listOfTasks.remove(index);
    }

    /**
     * Adds a new task to the list.
     *
     * @param task the task to be added
     */
    public void add(Task task) {
        this.listOfTasks.add(task);
    }

    /**
     * return the entire list of tasks.
     *
     * @return the array containing the list of tasks
     */
    public ArrayList<Task> getList() {
        return this.listOfTasks;
    }
}
