public abstract class Task {
    protected final String taskName;
    protected boolean taskComplete;

    public Task(String taskName) {
        this.taskName = taskName;
        this.taskComplete = false;
    }

    /**
     * Returns the icon that identifies whether the task has been completed or not.
     * @return '[X]' if completed ant '[ ]' if not.
     */
    protected String getStatusIcon() {
        return (taskComplete ? "[X]" : "[ ]");
    }

    /**
     * Marks the task as complete.
     */
    public void markAsComplete() {
        this.taskComplete = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void markAsIncomplete() {
        this.taskComplete = false;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + this.taskName;
    }
}
