package duke.task;

public abstract class Task {
    protected final String title;
    protected boolean status;

    public Task(String title, boolean status) {
        this.title = title;
        this.status = status;
    }

    /**
     * Returns icon that represents whether the given task has been completed or not.
     * @return '[X]' if task completed and '[ ]' if not
     */
    protected String getStatusIcon() {
        return (this.status ? "[X]" : "[ ]");
    }

    public String getTitle() {
        return this.title;
    }

    /**
     * Marks the given task as complete.
     */
    public void markAsComplete() {
        this.status = true;
    }

    /**
     * Marks the given task as incomplete.
     */
    public void markAsIncomplete() {
        this.status = false;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + this.title;
    }
}
