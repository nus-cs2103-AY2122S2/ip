package dazz.task;

/**
 * Represents a generic <code>Task</code>
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a <code>Task</code> and is not completed by default.
     * @param description Description of this <code>Task</code>.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a task and is completed depending on <code>isDone</code>.
     * @param description Description of this <code>Task</code>.
     * @param isDone Done or undone <code>Task</code>.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the icon that represents this <code>Task</code> is done or undone.
     * "X" if done and " " otherwise.
     * @return Icon representing this <code>Task</code>. is done or undone
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Gets the boolean representation of whether this <code>Task</code> is done.
     * @return True if done and false otherwise.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Gets the description of this <code>Task</code>.
     * @return Description of this <code>Task</code>.
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
     * Sets this <code>Task</code> to done.
     */
    public void setDone() {
        if (!hasAlreadyMark()) {
            this.isDone = true;
        }
    }

    /**
     * Sets this <code>Task</code> to undone.
     */
    public void setUndone() {
        if (!hasAlreadyUnmark()) {
            this.isDone = false;
        }
    }

    /**
     * Reformats this <code>Task</code> to be stored in a text file.
     * @return Text that would be stored as in a file.
     */
    public String writeToFile() {
        int isDone = this.getIsDone() ? 1 : 0;
        return isDone + " === " + this.description;
    }

    /**
     * String representation of this <code>Task</code>.
     * @return String representation of this <code>Task</code>.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
