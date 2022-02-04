package duke;

import java.util.ArrayList;
import java.util.List;

import duke.task.Task;

/**
 * An instance of TaskList that stores all the tasks.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Instantiates a new empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Instantiates a new TaskList and populates it with the content of the
     * parameter provided.
     *
     * @param tasks the tasks
     */
    public TaskList(List<String> tasks) {
        this.tasks = new ArrayList<>();
        tasks.forEach(entry -> { // parse entries
            try {
                this.tasks.add(Parser.parseStringToTask(entry));
            } catch (DukeException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Gets tasks.
     *
     * @return the tasks
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns `true` if this TaskList contains no `Task`.
     *
     * @return `true` if this TaskList contains no `Task`
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Get `Task` at the specified position in this `TaskList`
     *
     * @param index index of the `Task` to get
     * @return the `Task` at the specified position in this `TaskList`
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Removes the `Task` at the specified position in this `TaskList`.
     * Shift the other `Task` to the left (subtracts one from their indices).
     * Returns the `Task` that was removed from the `TaskList`.
     *
     * @param index the index of the `task` to be removed
     * @return the `task` previously at the specified position
     */
    public Task removeTask(int index) {
        return this.tasks.remove(index);
    }

    /**
     * Returns the number of `Task` in this `TaskList`.
     *
     * @return the number of `Task` in this list
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Appends the specified `Task` to the end of this `TaskList`.
     *
     * @param task the task
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

}
