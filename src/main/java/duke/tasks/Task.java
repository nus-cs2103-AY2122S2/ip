package duke.tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    public void mark() {
        isDone = true;
    }

    public void unMark() {
        isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTaskData() {
        return "[" + getStatus() +"] " + description;
    }

    public String toString() {
        return "[" + getStatus() +"] " + description;
    }
}
