package apollo.tasks;

import java.util.ArrayList;

/**
 * List of {@code Task} objects.
 * Implements {@code java.io.Serializable} interface.
 */
public class TaskList implements java.io.Serializable{

    private final ArrayList<Task> taskList;

    /**
     * Constructor for {@code TaskList}.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds supplied task to taskList.
     *
     * @param newTask Task to be added to taskList.
     */
    public void addTask(Task newTask) {
        taskList.add(newTask);
    }

    /**
     * Deletes task of supplied index from taskList.
     *
     * @param i Index of task.
     * @return Deleted task.
     */
    public Task deleteTask(int i) {
        Task deleted = taskList.get(i);
        taskList.remove(i);
        return deleted;
    }

    /**
     * Marks task of supplied index as done or not done.
     *
     * @param i Index of task.
     * @param isDone Boolean if task is done.
     * @return Marked task.
     */
    public Task markTask(int i, boolean isDone) {
        taskList.get(i).markAs(isDone);
        return taskList.get(i);
    }

    /**
     * Returns current number of tasks.
     *
     * @return Number of tasks.
     */
    public int taskCount() {
        return taskList.size();
    }

    /**
     * Gets String representation of task of supplied index.
     *
     * @param i Index of task
     * @return String representation of task.
     */
    public String getTaskString(int i) {
        return taskList.get(i).toString();
    }

    public String getTaskDescription(int i) {
        return taskList.get(i).getDescription();
    }
}
