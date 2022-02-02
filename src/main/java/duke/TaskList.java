package duke;

import duke.task.Task;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
      
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
     * Returns string representation of list.
     *
     * @return List of all tasks.
     */
    public String toString() {
        String string = "";
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String status = task.isMarked() ? "X" : " ";
            string += String.format("%d. %s", i, task.toString());
        }
        return string;
    }

}