package juke.common;

import juke.exception.JukeTaskListEmptyException;
import juke.task.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Abstraction for list of tasks.
 */
public class TaskList implements Iterable<Task> {
    private final ArrayList<Task> taskList;
    
    /**
     * Constructor to initialize empty internal list.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }
    
    /**
     * Constructor to initialize internal list with elements of a collection.
     *
     * @param tasks Collection of tasks.
     */
    public TaskList(Collection<Task> tasks) {
        this.taskList = new ArrayList<>();
        this.taskList.addAll(tasks);
    }
    
    /**
     * Adds a task to the end of the list.
     *
     * @param task Task to add.
     * @return True.
     */
    public boolean add(Task task) {
        return this.taskList.add(task);
    }
    
    /**
     * Removes the task at a specific index.
     * Returns null if index out of bounds.
     *
     * @param index Index to remove.
     * @return Task at the index.
     */
    public Task remove(int index) {
        try {
            return this.taskList.remove(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
    
    /**
     * Returns the task at a specific index.
     * Returns null if index out of bounds.
     *
     * @param index Index to return.
     * @return Task at the index.
     */
    public Task get(int index) {
        try {
            return this.taskList.get(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
    
    /**
     * Returns the number of tasks in the list.
     *
     * @return Number of tasks.
     */
    public int size() {
        return this.taskList.size();
    }
    
    public String[] list() throws JukeTaskListEmptyException {
        if (this.taskList.size() == 0) {
            throw new JukeTaskListEmptyException("list");
        }
        String[] strs = new String[this.taskList.size()];
        for (int i = 0; i < this.taskList.size(); i++) {
            strs[i] = this.taskList.get(i).toString();
        }
        return strs;
    }
    
    /**
     * Queries for all tasks containing the given string.
     *
     * @param query Query string.
     * @return List containing the results of the query.
     */
    public List<Task> search(String query) {
        ArrayList<Task> queryList = new ArrayList<>();
        for (Task task : this.taskList) {
            if (task.getDescription().contains(query)) {
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
