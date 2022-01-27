package pikabot.task;

/**
 * Represents a task with a description and keeps track of whether the task is done.
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieves status of Task.
     *
     * @return String indicating whether task is done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Updates status of Task to done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Updates status of Task to not done.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Retrieves description of Task.
     *
     * @return String description of Task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Converts Task to a formatted string.
     *
     * @return Formatted string representation of Task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
