package duke;

import java.util.ArrayList;

import duke.task.Task;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this(new ArrayList<Task>());
    }

    /**
     * Gets task at index n.
     *
     * @param n Specified index to fetch.
     */
    public Task get(int n) {
        return tasks.get(n);
    }

    /**
     * Adds specified task to list.
     *
     * @param task Specified task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes task at specified index.
     *
     * @param n List index of the task to delete.
     */
    public void deleteTask(int n) {
        tasks.remove(n);
    }

    /**
     * Marks task at specified index as done.
     *
     * @param n List index of the task to mark.
     */
    public void markTask(int n) {
        tasks.get(n).mark();
    }

    /**
     * Marks task at specified index as not done.
     *
     * @param n List index of the task to unmark.
     */
    public void unmarkTask(int n) {
        tasks.get(n).unmark();
    }

    /**
     * Finds tasks which match a specified string.
     *
     * @param str String to match.
     * @return Matching tasks.
     */
    public TaskList find(String str) {
        TaskList matchingTasks = new TaskList();
        for (Task task : tasks) {
            if (task.contains(str)) {
                matchingTasks.addTask(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Returns string representation of list.
     *
     * @return List of all tasks.
     */
    public String toString() {
        String string = "";
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            string += String.format("%d. %s", i, task.toString());
        }
        return string;
    }

    /**
     * Returns string representation of list for storage.
     *
     * @return Data of all tasks.
     */
    public String toDataString() {
        String string = "";
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            string += String.format("%s\n", task.toDataString());
        }
        return string;
    }

}
