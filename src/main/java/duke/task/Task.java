package duke.task;

/**
 * Represents a task to be done. This task possesses a state/status that is by default not done.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task that is initially undone.
     *
     * @param description Description of Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        // mark done task with X
        return (isDone ? "X" : " ");
    }
    public String getTaskDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getTaskDescription();
    }

    public abstract String toSave();
}
