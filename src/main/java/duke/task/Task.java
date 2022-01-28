package duke.task;

/**
 * Represents the tasks the user stores and
 * keeps track whether the task is done.
 */
public class Task {

    /** String to represent about the task. */
    protected String description;

    /** Boolean to keep track whether task is done. */
    protected boolean isDone;

    /** Type of task. */
    public TaskType type;

    /**
     * Constructor to create Task object.
     *
     * @param description string representing the task.
     */
    public Task(String description, TaskType type) {
        this.description = description;
        this.type = type;
        isDone = false;
    }

    /**
     * Outputs boolean to show whether task is done or not.
     *
     * @return true if task is done, false otherwise.
     */
    protected boolean getIsDone() {
        return isDone;
    }

    /**
     * Outputs the icon representing the
     * status of the task.
     *
     * @return status Icon as string.
     */
    protected String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the text description of the task.
     *
     * @return text description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the task as done. Sets isDone
     * to true.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Sets the task as undone. Sets isDone
     * to false.
     */
    public void unmarkAsDone() {
        isDone = false;
    }

    /**
     * Outputs the type of the task.
     *
     * @return TaskType representing the task.
     */
    public TaskType getType() {
        return type;
    }

    /**
     * Outputs a string description of the task.
     *
     * @return description of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}