package juke.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import juke.Juke;
import juke.exception.JukeEmptyTaskListException;
import juke.task.Task;

/**
 * Abstraction for list of tasks.
 */
public class TaskList implements Iterable<Task> {
    /**
     * Reference to the Juke instance.
     */
    private final Juke juke;

    /**
     * Internal list for tasks.
     */
    private final ArrayList<Task> taskList;

    /**
     * Constructor to initialize empty internal list.
     *
     * @param instance Instance of Juke.
     */
    public TaskList(Juke instance) {
        this.juke = instance;
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructor to initialize internal list with elements of a collection.
     *
     * @param tasks Collection of tasks.
     * @param instance Instance of juke.
     */
    public TaskList(Collection<Task> tasks, Juke instance) {
        this.juke = instance;
        taskList = new ArrayList<>();
        taskList.addAll(tasks);
    }

    /**
     * Adds a task to the end of the list.
     *
     * @param task Task to add.
     * @return True.
     */
    public boolean add(Task task) {
        return taskList.add(task);
    }

    /**
     * Removes the task at a specific index.
     * Returns null if index out of bounds.
     *
     * @param index Index to remove.
     * @return Task at the index.
     * @throws IndexOutOfBoundsException Throws if index is out of bounds.
     */
    public Task remove(int index) throws IndexOutOfBoundsException {
        return taskList.remove(index);
    }

    /**
     * Returns the task at a specific index.
     * Returns null if index out of bounds.
     *
     * @param index Index to return.
     * @return Task at the index.
     * @throws IndexOutOfBoundsException Throws if index is out of bounds.
     */
    public Task get(int index) throws IndexOutOfBoundsException {
        return taskList.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Number of tasks.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Lists the tasks in the task list.
     *
     * @return String array of task descriptions.
     * @throws JukeEmptyTaskListException Throws if task list is empty.
     */
    public String[] list() throws JukeEmptyTaskListException {
        if (taskList.size() == 0) {
            throw new JukeEmptyTaskListException();
        }
        String[] strs = new String[taskList.size()];
        for (int i = 0; i < taskList.size(); i++) {
            strs[i] = taskList.get(i).toString();
        }
        return strs;
    }

    /**
     * Marks the task at the given index.
     * Returns false if the index is invalid.
     *
     * @param index Index to return.
     * @throws IndexOutOfBoundsException Throws if index is out of bounds.
     */
    public void markTask(int index) throws IndexOutOfBoundsException {
        taskList.get(index).markAsDone();
    }

    /**
     * Unmarks the task at the given index.
     * Returns false if the index is invalid.
     *
     * @param index Index to return.
     * @throws IndexOutOfBoundsException Throws if index is out of bounds.
     */
    public void unmarkTask(int index) throws IndexOutOfBoundsException {
        taskList.get(index).markAsNotDone();
    }

    /**
     * Queries for all tasks containing the given string.
     *
     * @param query Query string.
     * @return List containing the results of the query.
     */
    public List<Task> search(String query) {
        ArrayList<Task> queryList = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getDescription().toLowerCase(Locale.ROOT).contains(query.toLowerCase(Locale.ROOT))) {
                queryList.add(task);
            }
        }
        return queryList;
    }

    /**
     * Returns the iterator over the elements of the list.
     *
     * @return Iterator.
     */
    @Override
    public Iterator<Task> iterator() {
        return taskList.iterator();
    }
}
