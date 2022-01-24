package duke.task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Represents TaskList class.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Represents TaskList class for modify tasks.
     *
     * @param tasks tasks for list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getByIndex(int index) {
        return tasks.get(index);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public TaskList set(int index, Task task) {
        tasks.set(index, task);
        return new TaskList(tasks);
    }

    /**
     * Represents TaskList class for adding new task.
     *
     * @param task task to be added.
     */
    public TaskList add(Task task) {
        tasks.add(task);
        return new TaskList(tasks);
    }

    /**
     * Represents TaskList class for removing task.
     *
     * @param index tasks index for removing.
     */
    public TaskList remove(int index) {
        tasks.remove(index);
        return new TaskList(tasks);
    }
}
