package duke.task;

/**
 * Represents a task in the Duke application.
 *
 * @author Zheng Teck
 * @version 1.0
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor to create a task.
     *
     * @param description Description of task stored.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor to create a task.
     *
     * @param description Description of task stored.
     * @param isDone If the task is completed.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * This method returns the type of this task.
     *
     * @return Returns the task type in String.
     */
    protected abstract String getType();

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done duke.task with X
    }

    protected boolean getIsDone() {
        return isDone;
    }

    protected void markAsDone() {
        this.isDone = true;
    }

    /**
     * This method formats this event into a format that is used for saving.
     *
     * @return Returns formatted String to for saving to storage.
     */
    public String formatToSave() {
        return this.getType() + "|" + (this.getIsDone() ? "1" : "0") + "|" + this.description;
    };

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}