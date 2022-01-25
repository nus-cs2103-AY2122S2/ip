package main.java.mike;

abstract class Task {
    protected final String name;
    protected final boolean isDone;

    public Task(String name) {
        this(name, false);
    }

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public String getTaskName() {
        return this.name;
    }

    abstract Task markAsDone();

    abstract Task markAsUndone();

    abstract String convertToStoredTaskFormat();

    @Override
    public String toString() {
        return String.format("name of task: %s", this.name);
    }
}
