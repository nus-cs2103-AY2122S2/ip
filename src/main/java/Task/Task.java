package Task;

/**
 * The type Task.
 */
public class Task {
    /**
     * The Description of the task.
     */
    public String description;
    /**
     * The completion status of the task.
     */
    public boolean isDone;

    /**
     * Instantiates a new Task.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the completion status icon.
     *
     * @return the completion status icon
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Mark the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Mark the task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
