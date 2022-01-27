package duke.main;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Represents a list of Task objects.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a new empty TaskList instance.
     */
    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    /**
     * Constructs a new TaskList instance initialized to contain the specified tasks.
     *
     * @param tasks The Arraylist whose elements are to be placed in this TaskList instance.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs a new TaskList instance initialized to contain the specified tasks.
     *
     * @param zeroBasedIndex Index of the Task to be marked or unmarked.
     * @param isDone Boolean indicating mark or unmark action. A true value indicates mark
     *               and false value indicates unmark.
     * @throws DukeException If the given zeroBasedIndex is out of bounds.
     */
    public void markTask(int zeroBasedIndex, boolean isDone) throws DukeException {
        try {
            tasks.get(zeroBasedIndex).mark(isDone);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(DukeException.ERROR_INVALID_INDEX);
        }
    }

    /**
     * Adds a new Task to the TaskList instance.
     *
     * @param task The Task object to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a Task object from the TaskList instance.
     *
     * @param zeroBasedIndex Index of the Task object to be deleted.
     * @throws DukeException If the given zeroBasedIndex is out of bounds.
     */
    public void delete(int zeroBasedIndex) throws DukeException {
        try {
            tasks.remove(zeroBasedIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(DukeException.ERROR_INVALID_INDEX);
        }
    }

    /**
     * Returns a Task object from the TaskList instance.
     *
     * @param zeroBasedIndex Index of the Task object to be returned.
     * @throws DukeException If the given zeroBasedIndex is out of bounds.
     */
    public Task getTask(int zeroBasedIndex) throws DukeException {
        try {
            return this.tasks.get(zeroBasedIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(DukeException.ERROR_INVALID_INDEX);
        }
    }

    /**
     * Returns an ArrayList representing the TaskList instance.
     *
     * @return An ArrayList representing the TaskList instance.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the number of Task objects in the TaskList instance.
     *
     * @return Number of Task objects in the TaskList instance.
     */
    public int getSize() {
        return tasks.size();
    }

    public ArrayList<Task> findTasks(String searchDescription) {
        return new ArrayList<Task>(tasks.stream()
                .filter(task -> task.hasSubstring(searchDescription))
                .collect(Collectors.toList()));
    }
}
