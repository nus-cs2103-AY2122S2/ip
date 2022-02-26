package butler.task;

public class Task {

    /** Description of what needs to be done for the task */
    private String description;
    /** Is the task complete or not */
    private boolean isDone;

    /**
     * Constructs a task object with a provided description.
     *
     * @param description description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the task description.
     *
     * @return String Task Description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the task description to a new given description.
     *
     * @param description New given description.
     * @return String Task Description.
     */
    public String setDescription(String description) {
        this.description = description;
        return this.description;
    }

    /**
     * Returns symbolic representation of whether task is done.
     *
     * @return String Representation of task completeness as a X.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Marks the task as done by toggling the boolean to true.
     *
     * @return Boolean Whether the task is complete.
     */
    public boolean markAsDone() {
        this.isDone = true;
        return isDone;
    }

    /**
     * Marks the task as not done by toggling the boolean to false.
     *
     * @return Boolean Whether the task is complete.
     */
    public boolean markAsUndone() {
        this.isDone = false;
        return this.isDone;
    }

    /**
     * Represents Task object as a String.
     *
     * @return String Representation of task as a whole.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] "
                + this.description + "\n";
    }
}
