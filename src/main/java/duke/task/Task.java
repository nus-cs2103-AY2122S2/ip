package duke.task;

/**
 * Represents a user's task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new task.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status of the task.
     *
     * @return a String symbol representing if the task is done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Produces the representation of the task in save file format.
     *
     * @return a string representation of the task to be used in storage
     */
    public abstract String getAppendData();

    /**
     * Checks if the task has a date.
     *
     * @return whether the task contains a date or not
     */
    public abstract boolean isHasDate();

    /**
     * Checks if the task has a time.
     *
     * @return whether the task contains a date or not
     */
    public abstract boolean isHasTime();

    public boolean hasKeyword(String keyword) {
        return description.contains(keyword);
    }

    /**
     * Produces a string representation of the task.
     *
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
