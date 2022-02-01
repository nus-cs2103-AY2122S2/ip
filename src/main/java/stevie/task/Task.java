package stevie.task;

import java.text.SimpleDateFormat;

/**
 * Represents a simple task.
 */
public abstract class Task {
    /**
     * A formatter to format Date object into a readable string.
     */
    protected static SimpleDateFormat formatter =
            new SimpleDateFormat("dd/MM/yyyy HH:mm");
    /**
     * Name of the task
     */
    protected String name;
    /**
     * Status of the task
     */
    protected boolean isDone;

    /**
     * Constructor for a Task.
     *
     * @param name the name of the task
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Marks task as done.
     */
    public void done() {
        this.isDone = true;
    }

    /**
     * Marks task as undone.
     */
    public void undone() {
        this.isDone = false;
    }

    /**
     * Checks if task name contains a query string.
     *
     * @param query string of keyword to match against task name
     * @return true if name contains keyword else false
     */
    public boolean contains(String query) {
        return name.contains(query);
    }

    /**
     * Returns a string representing the task's name.
     *
     * @return the name of the task
     */
    @Override
    public String toString() {
        String status = isDone ? "[X] " : "[ ] ";
        return status + this.name;
    }

    /**
     * Generates a formatted string to be written to a .txt save file. Must be overridden by
     * classes that extends Task.
     *
     * @return a formatted string that can be read to restore the task
     */
    public abstract String generateTaskSaveData();
}
