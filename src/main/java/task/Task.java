package task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String toFileString() {
        return String.format("|%d|%s", isDone ? 1 : 0, description);
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
