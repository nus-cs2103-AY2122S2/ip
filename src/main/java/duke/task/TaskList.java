package duke.task;

import java.util.ArrayList;

/**
 * A list of Task objects.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the number of tasks in TaskList.
     *
     * @return the number of tasks in TaskList.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the Task object in TaskList specified by the index.
     *
     * @param i the task number to get.
     * @return the Task to get from TaskList.
     */
    public Task get(int i) {
        return this.tasks.get(i);
    }

    /**
     * Adds a Task object to TaskList.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes a Task object from Tasklist.
     *
     * @param i Task number to be removed.
     */
    public void remove(int i) {
        this.tasks.remove(i);
    }

    /**
     * @param i Task number.
     * @return the String representation of the specified task number to be displayed.
     */
    public String getTaskStatement(int i) {
        return this.get(i).toString();
    }

    /**
     * Checks if a given task number exists in tasklist.
     *
     * @param taskNumber input task number.
     * @return whether number is out of bounds in tasklist.
     */
    public boolean isOutOfBounds(int taskNumber) {
        return taskNumber > getSize() || taskNumber <= 0;
    }
}
