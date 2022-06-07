package duke.task;

/**
 * Represents a task that needs to be done.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Class constructor.
     *
     * @param description description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the icon representing the status (done/not done) of this task.
     *
     * @return the icon representing the status (done/not done) of this task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark task that is done with X
    }

    /**
     * Marks this task as done.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Unmarks this task as not done.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Returns the string representation of this task formatted as the way it is to be stored in disk.
     *
     * @return the string representation of this task formatted as the way it is to be stored in disk.
     */
    public String toFileFormat() {
        return description;
    }

    /**
     * Returns the string representation of this task.
     *
     * @return the string representation of this task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
