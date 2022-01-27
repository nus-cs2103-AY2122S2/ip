package task;

public abstract class Task {
    public String description;
    public boolean isDone = false;

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
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