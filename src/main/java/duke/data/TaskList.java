package duke.data;

import java.util.List;
import java.util.ArrayList;

import duke.data.task.Task;

public class TaskList {

    private List<Task> tasks; 

    public TaskList() {
        this.tasks = new ArrayList<>();
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
     * @return The deleted task.
     */
    public Task delete(int index) {
        return this.tasks.remove(index);
    }

    /**
     * Marks the task in the specified index as done.
     *
     * @param index The index of the task.
     * @return The task that has been marked.
     */
    public Task mark(int index) {
        Task t = this.tasks.get(index);
        t.markAsDone();
        return t;
    }

    /**
     * Removes the mark of the task in the specified index.
     *
     * @param index The index of the task.
     * @return The task that has been unmarked.
     */
    public Task unmark(int index) {
        Task t = this.tasks.get(index);
        t.unmarkAsDone();
        return t;
    }

    /**
     * Gets the number of tasks in the task list。
     *
     * @return The number of tasks.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Turns the task list to java List.
     *
     * @return A list of tasks.
     */
    public List<Task> toList() {
        return tasks;
    }

    /**
     * Shows the information of all tasks in the task list.
     *
     * @return The string representation of task list.
     */
    public String toString() {
        String msg = "";
        for (int i = 1; i <= tasks.size(); i++) {
            msg += "\t " + i + "." + tasks.get(i-1).toString() + "\n";
        }
        return msg;
    }

    public void restore(Memento memento) {
        this.tasks = memento.getSavedTasks();
        System.out.println(memento);
        System.out.println(tasks);
    }

    public Memento takeSnapshot() {
        return new Memento(new ArrayList<>(this.tasks));
    }

    public static class Memento {
        private final List<Task> tasks;

        private Memento(List<Task> tasks) {
            this.tasks = tasks;
        }

        private List<Task> getSavedTasks() {
            return tasks;
        }
    }
}
