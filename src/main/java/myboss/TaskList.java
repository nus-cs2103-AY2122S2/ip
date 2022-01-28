package myboss;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    public ArrayList<Task> taskList;
    public static int size = 0;

    /**
     * Creates a TaskList Object with the specified taskList.
     *
     * @param taskList list of tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
        this.size = taskList.size();
    }

    /**
     * Returns the list of tasks.
     *
     * @return list of tasks.
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Returns the size of the task list.
     *
     * @return size of task list
     */
    public static int getSize() {
        return size;
    }

    /**
     * Returns the task at the specified index
     *
     * @param index index of task.
     * @return task at specified index.
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Adds the specified task to the task list and increments the size.
     *
     * @param task task to be added to the task list.
     */
    public void addTask(Task task) {
        taskList.add(task);
        size++;
    }

    /**
     * Returns the deleted task at specified index.
     *
     * @param index index of task to be deleted.
     * @return deleted task.
     */
    public Task deleteTask(int index) {
        size--;
        return taskList.remove(index);
    }
}
