package duke.task;

public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description.substring(0);
    }
}
