package ultoi.util;

import ultoi.task.Task;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a list of tasks.
 *
 * @author snoidetx
 * @version 0.0.0
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Creates a list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Creates a list of tasks from a given list of tasks.
     *
     * @param tasks Given list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the size of the task list.
     *
     * @return Number of tasks in the list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns a task from the list.
     *
     * @param index Index of the task.
     * @return Task at the index.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the list.
     *
     * @param index Index of the task to be deleted.
     * @return Deleted task.
     */
    public Task deleteTask(int index) {
        return this.tasks.remove(index);
    }

    /**
     * Marks a task in the list as done.
     *
     * @param index Index of the task to be marked as done.
     */
    public void mark(int index) {
        getTask(index).markAsDone();
    }

    /**
     * Marks a task in the list as undone.
     *
     * @param index Index of  the task to be marked as undone.
     */
    public void unmark(int index) {
        getTask(index).markAsUndone();
    }

    /**
     * Generates a message describing the number of tasks in the list.
     *
     * @return String describing the number of tasks in the list.
     */
    public String generateNumOfTasksMsg() {
        return "Now you have " + size() + " task(s) in total.";
    }

    public TaskList findTasksWith(String keyword) {
        List<Task> matchingTasks = new ArrayList<Task>();

        for (int i = 0; i < size(); i++) {
            if (tasks.get(i).getDescription().contains(keyword)) {
                matchingTasks.add(tasks.get(i));
            }
        }

        return new TaskList(matchingTasks);
    }

    /**
     * Returns the standard input strings of all the tasks in the list.
     *
     * @return Input strings of the tasks in the list.
     */
    public String toInputString() {
        String str = "";

        for (int i = 0; i < size(); i++) {
            str = str + (i + 1) + ". " + getTask(i).toInputString();
            if (i < size() - 1) {
                str = str + "\n";
            }
        }
        return str;
    }

    /**
     * Returns the string representation of the task list.
     *
     * @return String representation of the task list.
     */
    @Override
    public String toString() {
        String str = "";

        for (int i = 0; i < size(); i++) {
            str = str + (i + 1) + ". " + getTask(i).toString();
            if (i < size() - 1) {
                str = str + "\n";
            }
        }

        return str;
    }
}
