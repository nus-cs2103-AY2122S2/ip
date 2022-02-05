package duke.task;

/**
 * The type Task.
 */
public class Task {
    /**
     * The Description of the task.
     */
    private final String description;
    /**
     * The completion status of the task.
     */
    private boolean isDone;

    /**
     * Instantiates a new Task.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        assert !description.isEmpty();
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the completion status icon.
     *
     * @return the completion status icon
     */
    public String getStatusIcon() {
        /* mark done task with X */
        return (isDone ? "X" : " ");
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

    public String getDescription() {
        return this.description;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
