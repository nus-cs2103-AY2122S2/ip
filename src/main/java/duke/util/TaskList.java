package duke.util;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import duke.exception.DukeException;
import duke.exception.ErrorMessage;
import duke.task.Task;

/**
 * Contains a list of tasks and has operations to manipulate tasks in the list.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

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
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public int getSize() {
        return tasks.size();
    }

    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }
    
    private int convertIndexToZeroBased(int index) throws DukeException {
        int zeroBasedIndex = index - 1;
        if (zeroBasedIndex < 0 || zeroBasedIndex >= tasks.size()) {
            throw new DukeException(ErrorMessage.MESSAGE_INVALID_INDEX);
        }
        return zeroBasedIndex;
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index One-based index of the task to be retrieved.
     * @return Task at index - 1.
     * @throws DukeException if the index is invalid.
     */
    public Task getTask(int index) throws DukeException {
        return tasks.get(convertIndexToZeroBased(index));
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
     * Removes and returns the task at the specified index.
     *
     * @param index One-based index of the task to be deleted.
     * @return The deleted task.
     * @throws DukeException if the index is invalid.
     */
    public Task deleteTask(int index) throws DukeException {
        return tasks.remove(convertIndexToZeroBased(index));
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index One-based index of the task to be marked.
     * @throws DukeException if the index is invalid.
     */
    public void markAsDone(int index) throws DukeException {
        getTask(index).markAsDone();
        assert getTask(index).getIsDone() : "Task should be marked as done";
    }

    /**
     * Unmarks the task at the specified index as done.
     *
     * @param index One-based index of the task to be unmarked.
     * @throws DukeException if the index is invalid.
     */
    public void unmarkAsDone(int index) throws DukeException {
        getTask(index).unmarkAsDone();
        assert !getTask(index).getIsDone() : "Task should be unmarked as done";
    }

    /**
     * Filters and returns the task(s) that contain(s) the specified keyword.
     *
     * @param keyword Keyword to find the tasks with.
     * @return ArrayList of tasks containing the specified keyword.
     */
    public ArrayList<Task> getTasksWithKeyword(String keyword) {
        String regex = ".*\\b" + Pattern.quote(keyword.trim()) + "\\b.*";
        return tasks.stream()
                .filter(task -> task.getDescription().matches(regex))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
