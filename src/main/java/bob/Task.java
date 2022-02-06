package bob;

/**
 * Abstract parent class of all task types.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a Task object.
     *
     * @param description Description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for a Task object, given a status boolean value.
     *
     * @param description Description of the Task.
     * @param isDone      Boolean value describing status of the Task.
     */
    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the description of the Task.
     *
     * @return Description of the Task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a status icon depending on the status of this Task.
     *
     * @return A status icon, "X" or " ".
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Toggles the status of this Task.
     */
    public void toggleStatus() {
        this.isDone = !this.isDone;
    }

    /**
     * Changes the status of this Task to done.
     */
    public void toggleDone() {
        this.isDone = true;
    }

    /**
     * Changes the status of this Task to not done.
     */
    public void toggleNotDone() {
        this.isDone = false;
    }

    /**
     * Generates a string representing this Task object for saving.
     *
     * @return A string representing this Task for saving.
     */
    public abstract String generateSavedEntry();

    /**
     * Returns a string representation of this Task for display.
     *
     * @return A string representing this task for display.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description.strip();
    }
}
