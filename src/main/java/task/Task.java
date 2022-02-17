package task;

/**
 * Represents a task, containing its relevant fields and methods.
 */
public class Task {

    /** Name of task. */
    protected String description;
    /** Status to indicate if the task is done or not. */
    protected boolean isDone;
    /** Tag of task. */
    protected String tag;

    /**
     * Instantiates task with description input.
     * Sets the task status as false and task tag as null.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tag = null;
    }

    /**
     * Instantiates task with task description, task status and task tag
     * from data retrieved from the stored file.
     *
     * @param status Status of task retrieved.
     * @param description Description of task retrieved.
     * @param tag Tag of task retrieved.
     */
    public Task(String status, String description, String tag) {
        this.isDone = formatStatus(status);
        this.description = description;
        this.tag = tag;
    }

    /**
     * Returns string representation of the task status.
     * If task is done returns X, else an empty space.
     *
     * @return Task status. If task is done returns X, else an empty space.
     */
    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    /**
     * Returns task description.
     *
     * @return Description of task.
     */
    public String getDescription() {
        return this.description;
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
    public void markAsUnDone() {
        this.isDone = false;
    }

    /**
     * Tags the task as per tag label.
     *
     * @param tag Tag label.
     */
    public void tag(String tag) {
        this.tag = tag;
    }

    /**
     * Returns the status of task retrieved from the stored file.
     * If task is done returns true, else false.
     *
     * @param no The string value representing the status of the task.
     * @return Task status. If task is done returns true, else false.
     */
    private Boolean formatStatus(String no) {
        int status = Integer.parseInt(no);
        return status == 1 ? true : false;
    }

    /**
     * Returns the string representation of task with details
     * on the task status and description to be stored.
     *
     * @return String representation of task to be stored.
     */
    public String toSave() {
        String status = this.isDone ? ":1:" : ":0:";
        return status + this.description + " : " + this.tag;
    }

    /**
     * Returns the string representation of task with details
     * on the task status and description to be displayed.
     *
     * @return String representation of Task to be displayed.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
