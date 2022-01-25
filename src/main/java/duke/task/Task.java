package duke.task;

public abstract class Task {
    private String description;
    private boolean isDone;
    private final Type TASK_TYPE;

    public enum Type {
        TODO, DEADLINE, EVENT

    }

    public Task(String description, Type TASK_TYPE) {
        this.description = description;
        this.isDone = false;
        this.TASK_TYPE = TASK_TYPE;
    }

    public String getDescription() {
        return description;
    }

    public Type getType() {
        return this.TASK_TYPE;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
