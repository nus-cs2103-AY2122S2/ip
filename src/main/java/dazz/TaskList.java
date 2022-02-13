package dazz;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import dazz.exception.InvalidTaskIndexException;
import dazz.task.Task;

/**
 * Represents a task list.
 */
public class TaskList {
    protected List<Task> tasks;

    /**
     * Constructor to construct a <code>TaskList</code>.
     * @param tasks The list of <code>Task</code>.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a <code>Task</code> to this <code>TaskList</code>.
     * @param task The <code>Task</code> to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Marks the <code>Task</code> at the particular index
     * in this <code>TaskList</code>.
     * @param index The index of the <code>Task</code> to be marked.
     * @throws InvalidTaskIndexException If index is out of bound
     */
    public void mark(int index) throws InvalidTaskIndexException {
        if (index > this.getSize()) {
            throw new InvalidTaskIndexException();
        } else {
            this.getTask(index).setDone();
        }
    }

    /**
     * Unmarks the <code>Task</code> at the particular index
     * in this <code>TaskList</code>.
     * @param index The index of the <code>Task</code> to be unmarked.
     * @throws InvalidTaskIndexException If the index is out of bound.
     */
    public void unmark(int index) throws InvalidTaskIndexException {
        if (index > this.getSize()) {
            throw new InvalidTaskIndexException();
        } else {
            this.getTask(index).setUndone();
        }
    }

    /**
     * Deletes the <code>Task</code> at the particular index
     * in this <code>TaskList</code>.
     * @param index The index of the <code>Task</code> to be deleted.
     * @throws InvalidTaskIndexException If the index is out of bound.
     */
    public void delete(int index) throws InvalidTaskIndexException {
        if (index > this.getSize()) {
            throw new InvalidTaskIndexException();
        } else {
            this.tasks.remove(--index);
        }
    }

    /**
     * Gets the <code>Task</code> at the particular index
     * in this <code>TaskList</code>.
     * @param index The index of the <code>Task</code> to be retrieved.
     * @return Task The task retrieved from this <code>TaskList</code>.
     * @throws InvalidTaskIndexException If the index is out of bound.
     */
    public Task getTask(int index) throws InvalidTaskIndexException {
        if (index > this.getSize()) {
            throw new InvalidTaskIndexException();
        } else {
            return this.tasks.get(--index);
        }
    }

    /**
     * Lists out the <code>Task</code> in this <code>TaskList</code>.
     * @return <code>String</code> The string representation of the <code>Task</code>.
     */
    public String list() {
        return IntStream.rangeClosed(1, tasks.size())
                .mapToObj(x -> x + ". " + tasks.get(x - 1))
                .collect(Collectors.joining("\n"));
    }

    /**
     * Searches <code>Task</code> description that matches the keyword.
     * @param keyword The search criteria.
     * @return <code>String</code> The string representation of the <code>Task</code>
     * description contains the keyword.
     */
    public String search(String keyword) {
        List<Task> filteredTasks = tasks.stream()
                .filter(x -> x.getDescription().toLowerCase(Locale.ROOT).contains(keyword))
                .collect(Collectors.toList());

        return IntStream
                .rangeClosed(1, filteredTasks.size())
                .mapToObj(x -> x + ". " + filteredTasks.get(x - 1))
                .collect(Collectors.joining("\n"));
    }

    /**
     * Gets the number of <code>Task</code> in this <code>TaskList</code>.
     * @return the number of <code>Task</code> in this <code>TaskList</code>.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Gets all the <code>Task</code> in this <code>TaskList</code>.
     * @return The list of <code>Task</code>.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }
}
