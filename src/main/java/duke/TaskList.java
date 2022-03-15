package duke;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import duke.task.Task;

/**
 * Represents the list of user's tasks.
 */
public class TaskList implements Serializable {

    private ArrayList<Task> tasks;

    /**
     * Creates a new empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Initialize based on the list of tasks saved in the user's memory.
     *
     * @param tasks The saved list of tasks.
     */
    public TaskList(TaskList tasks) {
        this.tasks = new ArrayList<>(tasks.tasks);
    }

    /**
     * Marks the task corresponding to the index as completed.
     *
     * @param index The index of the task to be marked completed.
     * @return The completed task.
     */
    public Task mark(int index) {
        Task task = this.tasks.get(index);
        task.mark();
        return task;
    }

    /**
     * Unmarks the task corresponding to the index as completed.
     *
     * @param index The index of the task to be unmarked completed.
     * @return The incomplete task.
     */
    public Task unmark(int index) {
        Task task = this.tasks.get(index);
        task.unmark();
        return task;
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the list of tasks based on the index.
     *
     * @param index The index of the task to be deleted.
     * @return The deleted task.
     */
    public Task deleteTask(int index) {
        Task task = tasks.get(index);
        tasks.remove(index);
        return task;
    }

    /**
     * Counts the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int count() {
        return this.tasks.size();
    }

    public boolean isEmpty() {
        return this.tasks.size() == 0;
    }

    /**
     * Returns a TaskList containing tasks where description contains keyword.
     *
     * @param keyword the keyword to search for
     * @return filtered TaskList
     */
    public TaskList findTasks(String keyword) {
        TaskList tl = new TaskList(this);
        tl.tasks.removeIf(task -> !task.descriptionContains(keyword));
        return tl;
    }

    @Override
    public String toString() {
        List<String> taskStrings = IntStream.range(0, tasks.size())
                .mapToObj(i -> (i + 1) + ". " + tasks.get(i).toString())
                .collect(Collectors.toList());
        return String.join("\n", taskStrings);
    }
}
