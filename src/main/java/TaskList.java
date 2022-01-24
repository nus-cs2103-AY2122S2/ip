import java.util.LinkedList;

/**
 * This object represents a list of tasks
 *
 * @author Jan
 * @version 1.0
 */
public class TaskList {
    /** Stores the list of tasks */
    private LinkedList<Task> taskList;

    /**
     * Constructor for the TaskList object
     *
     * @param taskList  a list of tasks
     */
    public TaskList(LinkedList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Constructor for the taskList object
     */
    public TaskList() {
        this.taskList = new LinkedList<>();
    }

    /**
     * Returns the list of tasks as a linkedlist
     *
     * @return  list of tasks
     */
    public LinkedList<Task> toList() {
        return taskList;
    }

    /**
     * Returns the number of tasks in the list
     *
     * @return  number of tasks
     */
    public int taskAmt() {
        return taskList.size();
    }

    /**
     * Returns the task at the index given
     *
     * @param index     index of the task to be returned
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Adds a task to the tasklist
     *
     * @param task      task to be added
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Removes the task at the index given
     *
     * @param index     index of the task to be removed
     */
    public void removeTask(int index) {
        taskList.remove(index);
    }
}
