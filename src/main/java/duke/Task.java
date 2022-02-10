package duke;

import java.time.LocalDateTime;

public class Task {
    protected String description;
    protected boolean isDone;
    protected LocalDateTime dateTime;

    /**
     * Constructor for task
     * @param description
     * @param isDone
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.dateTime = LocalDateTime.parse("1700-01-01T00:00:00");
    }

    /**
     * Get the done status of task
     *
     * @return X if task is done, empty space if task is not done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    /**
     * Mark the task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Mark the task as undone
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Overrides toString method
     * @return description of task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
