package src.main.java.duke.task;

public class Task {
    protected String type;
    protected String description;
    protected boolean isDone;

    public Task(String type, String description, boolean isDone) {
        this.type = type;
        this.description = description;
        this.isDone = isDone;
    }

    public Task(String type, String description) {
        this(type, description, false);
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return this.isDone ? "[X] " + this.description
                : "[ ] " + this.description;
    }

    public boolean isDone() {
        return isDone;
    }

    public String type() {
        return type;
    }

    public String description() {
        return description;
    }
}
