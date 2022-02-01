package doge;

import doge.task.Task;

import java.util.ArrayList;

/**
 * Represents the list of tasks input by the user.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for class Task.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Another Constructor for class Task.
     *
     * @param tasks the list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task into the task list.
     *
     * @param task the new task to be added
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the task list based on its task number.
     *
     * @param pos the task number
     */
    public void deleteTask(int pos) {
        this.tasks.remove(pos);
    }

    /**
     * Marks a task in the task list based on its task number.
     *
     * @param pos the task number
     */
    public void markTask(int pos) {
        this.tasks.get(pos).mark();
    }

    /**
     * Unmarks a task in the task list based on its task number.
     *
     * @param pos the task number
     */
    public void unmarkTask(int pos) {
        this.tasks.get(pos).unmark();
    }

    /**
     * Retrieves a task from the task list based on its task number.
     *
     * @param pos the task number
     * @return the retrieved task
     */
    public Task getTask(int pos) {
        return this.tasks.get(pos);
    }

    /**
     * Returns the size of the task list.
     *
     * @return number of tasks in the task list currently
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns the list of tasks.
     *
     * @return the list of tasks
     */
    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }
}
