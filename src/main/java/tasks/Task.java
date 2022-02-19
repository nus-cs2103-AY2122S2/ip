package tasks;

/**
 * Represents work that needs to be done.
 */
public abstract class Task {
    protected final String description;
    protected boolean isDone;
    protected TaskPriority priority;

    /**
     * Returns a Task object that contains details of the
     * work that needs to be done.
     *
     * @param description describes the work that needs to be done.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.priority = TaskPriority.LOW;
    }

    protected Task(String description, boolean isDone, TaskPriority priority) {
        this.description = description;
        this.isDone = isDone;
        this.priority = priority;
    }

    protected String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    /**
     * Check if a task is completed or not.
     *
     * @return The completion status of a task.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Indicate a task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Indicate a task as uncompleted.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Describes the work that needs to be done.
     *
     * @return The description of the work that needs to be done.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Adjust the importance of a task.
     *
     * @param priority the importance of a task.
     */
    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    /**
     * Duplicates the current task.
     *
     * @return A duplicate of the current task.
     */
    public abstract Task clone();

    /**
     * Describes the current task's description and completion status.
     *
     * @return A string representation of the current task's description and
     * completion status.
     */
    @Override
    public String toString() {
        final String priority = "[" + this.priority + "]";
        final String completionStatus = "[" + this.getStatusIcon() + "]";
        return priority + completionStatus + " " + this.description;
    }
}
