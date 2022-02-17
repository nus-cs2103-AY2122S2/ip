package task;

/**
 * Represents a Task, containing its relevant fields
 * and methods.
 */
public class Task {

    /**
     * Name of Task.
     */
    protected String description;

    /**
     * Status to indicate if the Task is done or not.
     */
    protected boolean isDone;
    /**
     * Tag of activity.
     */
    protected String tag;

    /**
     * Creates Task as per description entered, initializing the
     * task as undone and tag as null.
     *
     * @param description Name of Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tag = null;
    }

    /**
     * Creates Task as per saved description, isDone status
     * and tag.
     *
     * @param description Name of Task.
     */

    /**
     * Creates Task as per saved description, isDone status and tag.
     * @param status String status retrieved.
     * @param description Description retrieved.
     * @param tag Tag retrieved.
     */
    public Task(String status, String description, String tag) {
        this.isDone = formatStatus(status);
        this.description = description;
        this.tag = tag;
    }

    /**
     * Returns String representation of the Task status.
     * X if done and an empty space otherwise.
     *
     * @return Task status
     */
    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    /**
     * Returns String representation of Task description.
     *
     * @return String representation of Task description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Mark the Task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Mark the Task as undone.
     */
    public void markAsUnDone() {
        this.isDone = false;
    }

    /**
     * Tag the task as per input.
     *
     * @param tag Name of tag.
     */
    public void tag(String tag) {
        this.tag = tag;
    }

    /**
     * Returns the boolean value of whether if the task is
     * completed or not.
     *
     * @param no The string containing the status.
     * @return True if Task is Done, False if not done.
     */
    private Boolean formatStatus(String no) {
        int status = Integer.parseInt(no);
        return status == 1 ? true : false;
    }

    /**
     * Returns the string representation with details
     * on the mark status and description.
     *
     * @return String representation of Task to save.
     */
    public String toSave() {
        String status = this.isDone ? ":1:" : ":0:";
        return status + this.description + " : " + this.tag;
    }

    /**
     * Returns the string representation with details
     * on the mark status and description.
     *
     * @return String representation of Task to display.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.description;
    }

}
