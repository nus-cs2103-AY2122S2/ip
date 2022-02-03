package duke.data;

import java.util.List;
import java.util.ArrayList;

import duke.data.task.Task;

public class TaskList {

    private List<Task> tasks; 

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /** Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task in the specified index.
     *
     * @param index The index of the task to be removed.
     */
    public Task delete(int index) {
        return this.tasks.remove(index);
    }

    /**
     * Marks the task in the specified index as done.
     *
     * @param index the index of the task
     * @return the task that has been marked
     */
    public Task mark(int index) {
        Task t = this.tasks.get(index);
        t.markAsDone();
        return t;
    }

    /**
     * Removes the mark of the task in the specified index.
     *
     * @param index the index of the task
     * @return the task that has been unmarked
     */
    public Task unmark(int index) {
        Task t = this.tasks.get(index);
        t.unmarkAsDone();
        return t;
    }

    /**
     * Gets the number of tasks in the task list。
     *
     * @return the number of tasks
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Turns the task list to java List.
     *
     * @return a list of tasks
     */
    public List<Task> toList() {
        return tasks;
    }

    /**
     * Shows the information of all tasks in the task list.
     *
     * @return the string representation of task list
     */
    public String toString() {
        String msg = "";
        for (int i = 1; i <= tasks.size(); i++) {
            msg += "\t " + i + "." + tasks.get(i-1).toString() + "\n";
        }
        return msg;
    }
}
