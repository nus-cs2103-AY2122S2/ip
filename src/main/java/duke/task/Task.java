package duke.task;

/**
 * Represents the task that the user has created in Duke.
 *
 * @author Terng Yan Long
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Instantiates a new instance of task, according to the task description.
     * By default, the new task is set to "not done".
     *
     * @param description Describes what needs to be done in this task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieves the status icon corresponding to the current status (isDone).
     *
     * @return "X" if task is already done, " " if task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Retrieves the task description.
     *
     * @return A string that represents the task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the current status for the task.
     *
     * @param isDone True/False depending if the task is already done.
     */
    public void setStatus(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Prepends a status checkbox that shows if the task is done.
     *
     * @return String containing a status icon that is prepended in front of the task description.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
