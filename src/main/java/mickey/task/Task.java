package mickey.task;

/**
 * Task object.
 */
public abstract class Task {
    /** Task description */
    private final String description;

    /** If task is done. */
    private boolean isDone = false;

    Task(String description) {
        this.description = description;
    }

    /**
     * Gets done status icon.
     *
     * @return Done status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Gets description of task.
     *
     * @return String of task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets is task is done.
     *
     * @return Boolean of whether task is done.
     */
    public boolean isDone() {
        return this.isDone;
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
