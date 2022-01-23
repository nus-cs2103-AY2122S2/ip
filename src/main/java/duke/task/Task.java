package duke.task;

/**
 * Represents a general task.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * A Task constructor to initialise a <code>Task</code> object. A <code>Task</code>
     * corresponds to a task represented by a String.
     * E.g., <code>do project</code>.
     * However, this is an abstract class so Task cannot be instantiated.
     *
     * @param description the description of the task to be done.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets the attribute of the task to be true.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Sets the attribute of the task to be false.
     */
    public void unmark() {
        isDone = false;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns the string representation of the <code>Task</code> task.
     *
     * @return the string representation of the <code>Task</code> task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description.substring(0);
    }
}
