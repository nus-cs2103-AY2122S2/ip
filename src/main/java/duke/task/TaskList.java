package duke.task;

import java.util.ArrayList;

/**
 * Class containing the list of Tasks loaded to the chat bot.
 */
public class TaskList {

    /**
     * List of task.
     */
    private ArrayList<Task> taskList;

    /**
     * Constructor to create empty task list.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Constructor to create TaskList object with loaded task.
     *
     * @param taskList list of tasks as an array list.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Retrieves the task at particular index.
     *
     * @param index index to retrieve task from.
     * @return task at index.
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Returns the size of the task list.
     *
     * @return size of list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task task to be added to the list.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Removes the task at particular index.
     *
     * @param index the index to remove task from.
     * @return task removed.
     */
    public Task remove(int index) {
        return taskList.remove(index);
    }

    /**
     * Outputs string describing the number of task in
     * the task list.
     *
     * @return string with number of tasks.
     */
    public String sizeDescription() {
        return "Now you have " + size() + (size() == 1 ? " task" : " tasks")
                + " in the list.";
    }
}
