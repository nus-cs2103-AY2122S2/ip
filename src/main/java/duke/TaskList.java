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
                result += (i + 1) + ". " + ls.get(i) + "\n";
            }
        }
        return result;
    }

    public void findAndSetName(String name, int index) {
        try {
            ls.get(index - 1).setName(name);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Provided index is invalid");
        }

    }

    public void findAndSetTime(String time, int index) {
        try {
            System.out.println("Hi");
            Task task = ls.get(index - 1);
            if (task instanceof Todo) {
                throw new IllegalArgumentException();
            }
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                deadline.setTime(time);
            }
            if (task instanceof Event) {
                Event event = (Event) task;
                event.setTime(time);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Task is a todo");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Provided index is invalid");
        }

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
