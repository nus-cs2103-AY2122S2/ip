package task;

/**
 * Abstract class for tasks.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task.
     * @param description the description of the task
     * @param isDone the status of the task
     */
    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets String that corresponds to the Task to be saved.
     * @return String save message for the task.
     */
    public abstract String getSaveMessage();

    /**
     * Marks the Task as Done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmark the Task as Done.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Returns the processed status of the task.
     * @return true if the task is done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Getter function for the task's description.
     * @return description of the task.
     */
    public String getDescription() {
        return this.description;
    }
}