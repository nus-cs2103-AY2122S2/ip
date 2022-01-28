package mickey.task;

/**
 * Task object.
 */
public abstract class Task {
    /** Task description */
    public String description;

    /** If task is done. */
    public boolean isDone = false;

    /**
     * Gets done status icon.
     *
     * @return Done status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks task as done.
     *
     * @return Task marked as done.
     */
    public Task markAsDone() {
        this.isDone = true;
        return this;
    }

    /**
     * Marks task as undone.
     *
     * @return Task marked as undone.
     */
    public Task markAsUndone() {
        this.isDone = false;
        return this;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}