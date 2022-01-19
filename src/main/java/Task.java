abstract class Task {
    protected String description;
    protected boolean isDone = false;

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public Task markAsDone() {
        this.isDone = true;
        return this;
    }

    public Task markAsUndone() {
        this.isDone = false;
        return this;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}