package duke.tasks;

import java.util.ArrayList;

/**
 * Encapsulates a list of user tasks
 *
 * @param <T> the type parameter
 */
public class TaskList<T extends Task> {
    /**
     * An ArrayList of tasks
     */
    private ArrayList<T> tasks;

    /**
     * Instantiates a new Task list.
     *
     * @param tasks list of existing tasks
     */
    @SuppressWarnings("unchecked")
    public TaskList(ArrayList<T> tasks) {
        this.tasks = tasks;
    }

    /**
     * Instantiates an empty Task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a new task to the task list.
     *
     * @param task the task to be added to the task list
     */
    public void add(T task) {
        this.tasks.add(task);
    }

    /**
     * Returns current length of the task list.
     *
     * @return the length of the task list
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Gets specified task object using index
     *
     * @param i the index of the task
     * @return the task at index i
     */
    public T get(int i) {
        return this.tasks.get(i-1);
    }

    /**
     * Removes a specified task object from task list
     *
     * @param i the index of the task object to be removed
     * @return the task object that was removed
     */
    public T remove(int i) {
        return this.tasks.remove(i-1);
    }

    /**
     * Marks a specified task's completion state.
     *
     * @param i    the index of task object to be marked or unmarked
     * @param done the state of completion to mark the specified task
     */
    public void markDone(int i, boolean done) {
        this.tasks.get(i-1).markDone(done);
    }

    /**
     * String representation of the task list
     *
     * @return string representation of the task list
     */
    @Override
    public String toString() {
        String result = "";
        for (int i=0; i<tasks.size(); i++) {
            result += (i+1) + ". " + tasks.get(i) + "\n";
        }
        return result;
    }
}