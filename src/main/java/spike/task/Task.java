package spike.task;

import java.time.LocalDateTime;

/**
 * Encapsulate information of a user task.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected LocalDateTime dateTime;

    /**
     * Constructor for spike.task.Task objects.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.dateTime = LocalDateTime.parse("1700-01-01T00:00:00");
    }

    public Task(String description, LocalDateTime dateTime) {
        this.description = description;
        this.isDone = false;
        this.dateTime = dateTime;
    }

    /**
     * Retrieve the task status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Changes status of task to be 'Done'.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Changes status of task to be 'Not done'.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the representative string for saving in data file.
     */
    public String toFileFormat() {
        return "";
    }

    /**
     * Format the string representation of spike.task.Task objects.
     * Gets the date and time for tasks with such information.
     */
    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    /**
     * Formats the string representation of spike.task.Task objects.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
