package spike.task;

import java.time.LocalDateTime;

/**
 * Encapsulate information of a user task.
 */
public class Task {
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HHmm";
    public static final String DATE_TIME_PATTERN_FOR_PRINT = "yyyy-MM-dd HH:mm";
    protected String description;
    protected boolean isDone;
    protected LocalDateTime dateTime;

    /**
     * Constructor for Task objects.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.dateTime = LocalDateTime.parse("1700-01-01T00:00:00");
    }

    /**
     * Alternative constructor for task associated with date and time.
     *
     * @param description task description
     * @param dateTime associated date and time
     */
    public Task(String description, LocalDateTime dateTime) {
        this.description = description;
        this.isDone = false;
        this.dateTime = dateTime;
    }

    /**
     * Retrieve the task status icon.
     *
     * @return status icon
     */
    private String getStatusIcon() {
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
     *
     * @return string format for saving
     */
    public String toFileFormat() {
        return "";
    }

    /**
     * Gets the date and time for tasks with such information.
     *
     * @return object containing the date and time information
     */
    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    /**
     * Formats the string representation of Task objects.
     *
     * @return string representation of a task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
