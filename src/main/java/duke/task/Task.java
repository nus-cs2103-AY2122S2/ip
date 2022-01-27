package duke.task;

public abstract class Task {
    protected final String title;
    protected boolean isComplete;

    public Task(String title, boolean isComplete) {
        this.title = title;
        this.isComplete = isComplete;
    }

    /**
     * Returns icon that represents whether the given task has been completed or not.
     * @return '[X]' if task completed and '[ ]' if not
     */
    protected String getStatusIcon() {
        return (this.isComplete ? "[X]" : "[ ]");
    }

    /**
     * Marks the given task as complete.
     */
    public void markAsComplete() {
        this.isComplete = true;
    }

    /**
     * Marks the given task as incomplete.
     */
    public void markAsIncomplete() {
        this.isComplete = false;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + this.title;
    }
}
