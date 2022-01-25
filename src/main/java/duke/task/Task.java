package duke.task;

/**
 * Represents a task to be completed.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Class Constructor.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the type of the task.
     *
     * @return The type of the task.
     */
    public abstract String getType();

    /**
     * Returns the string representation of the task.
     *
     * @return String representation of the status and description of the task.
     */
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + this.description;
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
     * Returns the description of the task.
     *
     * @return Description of task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status of the task.
     *
     * @return True if task is completed, false otherwise.
     */
    public boolean getIsDone() {
        return this.isDone;
    }
}
