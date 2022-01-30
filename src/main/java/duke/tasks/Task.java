package duke.tasks;

/**
 * Represents a generic task.
 */

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a generic task.
     *
     * @param description Task description.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Constructs a generic task.
     *
     * @param description Task description.
     * @param isDone If the task has is done or not.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Sets the status of task.
     *
     * @param isDone The status of the task.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        String flag = isDone ? "[X] " : "[ ] ";
        return flag + description;
    }

    /**
     * Expresses the task compact string.
     *
     * @return The string description of the task.
     */
    public String simpleString() {
        return description;
    }
}
