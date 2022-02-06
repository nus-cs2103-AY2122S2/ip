import java.util.ArrayList;

/**
 * TaskList class is used to store all the Task using an ArrayList
 * while providing various methods to manipulate the entries.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructor of TaskList with no parameters.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor of TaskList with another TaskList as reference..
     */
    public TaskList(TaskList taskList) {
        this.tasks = taskList.tasks;
    }

    /**
     * Returns the number of Tasks stored.
     *
     * @return Size of the ArrayList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Adds the current Task into the TaskList.
     *
     * @param currTask Task to be stored.
     */
    public void add(Task currTask) {
        this.tasks.add(currTask);
    }

    /**
     * Deletes the Task at the index.
     *
     * @param num Index of Task to be deleted.
     */
    public void delete(int num) {
        this.tasks.remove(num);
    }

    /**
     * Marks the Task at the index as done.
     *
     * @param num Index of task to be marked as done.
     */
    public void mark(int num) {
        this.tasks.get(num).setMark();
    }

    /**
     * Unmarks the Task at the index as not done.
     *
     * @param num Index of task to be marked as not done.
     */
    public void unmark(int num) {
        this.tasks.get(num).setUnmark();
    }

    /**
     * Returns the Task at the given index.
     *
     * @param num Index of the Task to return.
     * @return Task at the current index.
     * @throws IndexOutOfBoundsException When Task at index is no available.
     */
    public Task getAt(int num) throws IndexOutOfBoundsException {
        return this.tasks.get(num);
    }

    /**
     * Returns the string of the Task at the given index.
     *
     * @param num Index of the String of Task to return.
     * @return String representation of the Task.
     */
    public String getString(int num) {
        return this.tasks.get(num).toString();
    }

    /**
     * Checks if the current TaskList is empty.
     *
     * @return Boolean value of if the TaskList is empty.
     */
    public boolean isEmpty() {
        return this.tasks.size() == 0;
    }
}
