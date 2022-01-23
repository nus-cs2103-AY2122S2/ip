package duke.util;

import duke.exception.DukeException;
import duke.exception.ErrorMessage;
import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains a list of tasks and has operations to add/delete tasks in the list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a task list with the specified data.
     *
     * @param tasks List of tasks to be added to the task list.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index One-based index of the task to be retrieved.
     * @return Task at index - 1.
     * @throws DukeException if the index is invalid.
     */
    public Task getTask(int index) throws DukeException {
        int zeroBasedIndex = index - 1;
        if (zeroBasedIndex < 0 || zeroBasedIndex >= tasks.size()) {
            throw new DukeException(ErrorMessage.MESSAGE_INVALID_INDEX);
        }
        return tasks.get(zeroBasedIndex);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes the task at the specified index.
     *
     * @param index One-based index of the task to be deleted.
     * @throws DukeException if the index is invalid.
     */
    public void deleteTask(int index) throws DukeException {
        int zeroBasedIndex = index - 1;
        if (zeroBasedIndex < 0 || zeroBasedIndex >= tasks.size()) {
            throw new DukeException(ErrorMessage.MESSAGE_INVALID_INDEX);
        }
        tasks.remove(zeroBasedIndex);
    }

    /**
     * Marks the task at the specified index.
     *
     * @param index One-based index of the task to be marked.
     * @throws DukeException if the index is invalid.
     */
    public void markAsDone(int index) throws DukeException {
        getTask(index).markAsDone();
    }

    /**
     * Unmarks the task at the specified index.
     *
     * @param index One-based index of the task to be unmarked.
     * @throws DukeException if the index is invalid.
     */
    public void unmarkAsDone(int index) throws DukeException {
        getTask(index).unmarkAsDone();
    }

    public ArrayList<Task> getAllTasks() {
        return new ArrayList<Task>(tasks);
    }
}
