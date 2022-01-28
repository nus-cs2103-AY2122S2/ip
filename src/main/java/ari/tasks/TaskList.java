package main.java.ari.tasks;

import java.util.ArrayList;

/**
 * Contains a list of Tasks and methods that deal with Task
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructor of TaskList
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Returns size of the current list of Tasks
     *
     * @return size of TaskList
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Returns Task at index
     *
     * @param index index of Task
     * @return Task at index
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Returns the whole list of Tasks
     *
     * @return list of Tasks
     */
    public ArrayList<Task> getAllTasks() {
        return taskList;
    }

    /**
     * Adds Task to list
     *
     * @param task Task to add in list
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Marks Task at the index
     *
     * @param index index of Task to mark
     */
    public void markTask(int index) {
        Task task = getTask(index);
        task.markDone();
    }

    /**
     * Un-marks Task at the index
     *
     * @param index index of Task to unmark
     */
    public void unmarkTask(int index) {
        Task task = getTask(index);
        task.markNotDone();
    }

    /**
     * Deletes Task at the index
     *
     * @param index index of Task to delete
     */
    public void deleteTask(int index) {
        taskList.remove(index);
    }
}
