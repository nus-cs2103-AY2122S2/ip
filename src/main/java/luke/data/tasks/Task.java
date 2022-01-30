package luke.data.tasks;

/**
 * Implements a task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected boolean isFiltered;

    /**
     * Construct a task with the specified description.
     * All task by default are set to not done.
     *
     * @param description The specified description for the task.
     */
    public Task(String description) {
        this.description = description.stripTrailing();
        this.isDone = false;
        this.isFiltered = false;
    }

    /**
     * Returns an X if isDone is true, empty space if X is false.
     *
     * @return An X if isDone is true, empty space if X is false.
     */
    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets this task as not done.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Sets this task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns true if this task is done.
     *
     * @return True if this task is done.
     */
    public boolean isDone() {
        return isDone;
    }

    public void markAsFiltered() {
        isFiltered = true;
    }

    public void clearFilter() {
        isFiltered = false;
    }

    public boolean isNotFiltered() {
        return !isFiltered;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    /**
     * Returns the command string (user input) used to create this task.
     *
     * @return The command string (user input) used to create this task.
     */
    public abstract String getCommandString();
}
