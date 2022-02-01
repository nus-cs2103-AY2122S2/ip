package dukeclasses;

import java.util.ArrayList;

/**
 * Represents the data that is stored in Duke. Usually in the form of tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructor to instantiate an instance of TaskList.
     *
     * @param tasks ArrayList</Task> that represents the list of tasks the user had logged previously.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructor to instantiate an instance of TaskList if there were no tasks
     * logged by the user previously.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Updates the data of tasks in the taskList.
     *
     * @param index Integer that indicate the index of the task to be modified.
     * @param isDone Boolean that changes isDone field of Tasks.
     * @return Task that is modified.
     */
    public Task updateTask(int index, boolean isDone) {
        if (isDone) {
            tasks.get(index).setDone(true);
        } else {
            tasks.get(index).setDone(false);
        }
        return tasks.get(index);
    }

    /**
     * Returns the taskList in the form of an ArrayList</Task>
     *
     * @return Data of task in the form of an ArrayList</Task>
     */
    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    /**
     * Appends a task to the end of the taskList.
     *
     * @param task that is to be added to the end of the taskList.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task in the taskList.
     *
     * @param index Integer of the task to be deleted in the taskList.
     * @return Task that was deleted.
     */
    public Task deleteTask(int index) throws DukeException{
        if (index > tasks.size() - 1) {
            throw new DukeException();
        } else {
            return tasks.remove(index);
        }

    }

}
