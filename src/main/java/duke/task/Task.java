package duke.task;

/**
 * The type Task.
 */
public class Task {
    /**
     * The Description.
     */
    protected String description;
    /**
     * The Is done.
     */
    protected boolean isDone;

    /**
     * Instantiates a new Task.
     *
     * @param description the description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets status icon.
     *
     * @return the status icon
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return this.description; // mark done task with X
    }

    /**
     * Mark as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Mark as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.getDescription());
    }
}
