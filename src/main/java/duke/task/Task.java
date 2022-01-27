package duke.task;

/**
 * Abstract class that represents a task, which has a name and a completion status.
 */
public abstract class Task {
    protected final String title;
    protected boolean status;

    /**
     * Constructs a new Task object.
     * @param title The name given to the task
     * @param status Indicates whether the task has been completed or not.
     */
    public Task(String title, boolean status) {
        this.title = title;
        this.status = status;
    }

    /**
     * Returns icon that represents whether the given task has been completed or not.
     * @return '[X]' if task completed and '[ ]' if not
     */
    protected String getStatusIcon() {
        return (this.status ? "[X]" : "[ ]");
    }

    /**
     * Marks the given task as complete.
     */
    public void markAsComplete() {
        this.status = true;
    }

    /**
     * Marks the given task as incomplete.
     */
    public void markAsIncomplete() {
        this.status = false;
    }

    /**
     * Returns the task name and indicates whether it has been completed or not.
     * @return The task name with the completion status icon as a prefix
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + this.title;
    }
}
