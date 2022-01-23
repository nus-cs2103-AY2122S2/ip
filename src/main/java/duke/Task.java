package duke;

abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    abstract String getTaskType();

    abstract String getDate();

    public String getDescription() {
        return this.description;
    }

    public String toString() {
        return String.format("[%s]%s", getStatusIcon(), this.description);
    }
}
