package puke.task;

import java.time.LocalDateTime;

/**
 * Represents a task of different types.
 */
public abstract class Task {
    protected final String name;
    protected boolean isDone;

    /**
     * Initialises the task with the task name.
     *
     * @param taskName Name of the task.
     */
    Task(String taskName) {
        this.name = taskName;
        this.isDone = false;
    }

    /**
     * Checks if the task is marked as done.
     *
     * @return true if done; false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the name of the task.
     *
     * @return Task name.
     */
    public String getTaskName() {
        return name;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Check if the task name contains a specified string.
     *
     * @param s String to match in the task name.
     * @return True if the task name contains the string; False otherwise.
     */
    public boolean containKeyword(String s) {
        return this.name.contains(s);
    }

    /**
     * Returns the date/time object of the task (if applicable).
     *
     * @return The date/time of the task if it is a deadline/event; Or null otherwise.
     */
    abstract LocalDateTime getDate();

    /**
     * Generates a string to save the task information on the storage file.
     *
     * @return String representation of the task for the storage file.
     */
    abstract String toSaveString();


    /**
     * Returns a string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[" + (this.isDone() ? "✔" : "✘") // get the icon according to the status
                + "] " + this.name;
    }
}
