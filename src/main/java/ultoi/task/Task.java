package ultoi.task;

import ultoi.util.DateTime;

/**
 * Represents a task.
 *
 * @author snoidetx
 * @version 0.0.0
 */
public class Task {
    protected String description;
    private boolean isDone;

    /**
     * Creates a new task.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Generates a done symbol X if the task is done.
     */
    public String genDoneSymbol() {
        return isDone ? "X" : " ";
    }

    /**
     * Checks whether the task is done.
     *
     * @return True if the task is done.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Returns the standard input message to create this task.
     *
     * @return Input string.
     */
    public String toInputString() {
        return "task " + description;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return String representation of the task.
     */
    public String toString() {
        return "[" + genDoneSymbol() + "] " + description;
    }

    /**
     * Returns the date and time of the task.
     *
     * @return null.
     */
    public DateTime getDateTime() {
        return null;
    }
}
