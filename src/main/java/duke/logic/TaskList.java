package duke.logic;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Represents a list of tasks.
 *
 * @author Peter
 */
public class TaskList {
    /**
     * Internal ArrayList of tasks.
     */
    private final ArrayList<Task> tasks;

    /**
     * Constructor for an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor for a preset list of tasks.
     *
     * @param tasks List of tasks from which tasks are to be retrieved.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public void remove(int i) {
        this.tasks.remove(i);
    }

    public void clear() {
        this.tasks.clear();
    }

    public int size() {
        return this.tasks.size();
    }

    /**
     * Filters a copy of the internal ArrayList of tasks with respect to a given keyword.
     *
     * @param keyword Keyword that is to be indexed in each task.
     * @return Filtered copy of list of tasks.
     */
    public TaskList filter(String keyword) {
        ArrayList<Task> newTaskList = new ArrayList<>(this.tasks);
        newTaskList.removeIf(t -> !t.hasKeyword(keyword));

        return new TaskList(newTaskList);
    }

    /**
     * Converts the associated list of tasks to a form legible by the storage.
     *
     * @return Data representation of the associated list of tasks.
     */
    public String toData() {
        StringBuilder data = new StringBuilder();
        for (Task task : this.tasks) {
            data.append(task.toData()).append("\n");
        }
        return data.toString();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            result.append("\n").append(i + 1).append(". ").append(tasks.get(i));
        }
        return result.toString();
    }
}
