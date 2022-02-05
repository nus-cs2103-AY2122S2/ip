package duke;

/**
 * Represent Task with description
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new Task with description
     *
     * @param description Description of Task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns Status Icon of current task as a String representation
     *
     * @return String representation of current task status icon
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Returns Description of task
     *
     * @return String Description of task
     */
    public String getDescription() {
        return description;
    }


    /**
     * Set current task status to done
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Set current task status to not done
     */
    public void setUndone() {
        isDone = false;
    }

    /**
     * Returns String representation of {@code Task}.
     *
     * @return String representation of task of format {@code StatusIcon + Description}
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + this.description;
    }
}