package dazz.task;

/**
 * Represents a generic task
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task and is not completed by default.
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a task and is completed depending on <code>isDone</code>.
     * @param description Description of the task.
     * @param isDone Done or undone.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the icon that represents a task is done or undone.
     * "X" if done and " " otherwise.
     * @return Icon representing the task is done or undone
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Gets the boolean representation of whether the task is done.
     * @return True if done and false otherwise.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Gets the description of the task.
     * @return Description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    private boolean hasAlreadyMark() {
        return this.isDone;
    }

    private boolean hasAlreadyUnmark() {
        return !this.isDone;
    }

    /**
     * Sets the task to done.
     */
    public void setDone() {
        if (!hasAlreadyMark()) {
            this.isDone = true;
        }
    }

    /**
     * Sets the task to undone.
     */
    public void setUndone() {
        if (!hasAlreadyUnmark()) {
            this.isDone = false;
        }
    }

    /**
     * Reformats the task to be stored in a text file.
     * @return Text that would be stored as in a file.
     */
    public String writeToFile() {
        int isDone = this.getIsDone() ? 1 : 0;
        return isDone + " === " + this.description;
    }

    /**
     * String representation of the deadline task.
     * @return String representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
