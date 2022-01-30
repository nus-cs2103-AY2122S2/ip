package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a List of Tasks, that will be saved to/loaded from
 * when <code>Duke</code> is run.
 */


public class TaskList {
    private List<Task> ls;

    public TaskList(List<Task> ls) {
        this.ls = ls;
    }

    public TaskList() {
        this.ls = new ArrayList<>();
    }

    /**
     * Lists the string representation of all tasks currently in
     * the list.
     */
    public void listTasks() {
        for (int i = 0; i < ls.size(); i++) {
            System.out.println((i + 1) + ". " + ls.get(i));
        }
    }

    public String getTasksAsString() {
        String result = "";
        for (int i = 0; i < ls.size(); i++) {
            result = result + (i + 1) + ". " + ls.get(i) + "\n";
        }
        return result;
    }

    /**
     * Prints tasks in the list that has name containing the specified keyword.
     * @param keyword
     */
    public void findAndPrintTasks(String keyword) {
        for (int i = 0; i < ls.size(); i++) {
            if (ls.get(i).getName().contains(keyword)) {
                System.out.println((i + 1) + ". " + ls.get(i));
            }
        }
    }

    public String findAndGetTasks(String keyword) {

        String result = "";

        for (int i = 0; i < ls.size(); i++) {
            if (ls.get(i).getName().contains(keyword)) {
                result +=(i + 1) + ". " + ls.get(i) + "\n";
            }
        }
        return result;
    }

    /**
     * Sets the task at specified index in the list as done.
     * @param index
     * @return The task that was affected.
     */
    public Task setDone(int index) {
        ls.get(index).setDone();
        return ls.get(index);
    }

    /**
     * Sets the task at specified index in the list as undone.
     * @param index
     * @return The task that was affected.
     */
    public Task setUndone(int index) {
        ls.get(index).setUndone();
        return ls.get(index);
    }

    /**
     * Adds a task to the list.
     */
    public void addTask(Task toAdd) {
        ls.add(toAdd);
    }

    /**
     * Deletes the task at the specified index.
     * @param index
     * @return The task that was removed.
     */
    public Task deleteTask(int index) {
        Task toRemove = ls.get(index);
        ls.remove(index);
        return toRemove;
    }

    /**
     * Obtain the current size of the list.
     * @return int containing size of the list
     */
    public int getSize() {
        return ls.size();
    }

    /**
     * Utility getter method to obtain the underlying list.
     * @return the underlying List of tasks.
     */
    public List<Task> getList() {
        return this.ls;
    }

}
