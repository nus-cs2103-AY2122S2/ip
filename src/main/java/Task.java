/**
 * Represent a task that is stored by Duke.
 */
class Task {

    private boolean isDone;
    private String description;

    /**
     * Constructor of a task. Sets the description of the task, and set the isDone status to be false by default.
     *
     * @param description The description of the task.
     */
    protected Task(String description) {

        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the current task as done.
     */
    protected void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the current task as not done.
     */
    protected void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Obtains the status of the task.
     *
     * @return True if the task is done already, false otherwise.
     */
    protected boolean isDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Gets the icon that represents the status of the task.
     *
     * @return The icon.
     */
    private String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

}
