package task;

public abstract class Task {
    public String description;
    public boolean isDone;

    public Task (String task, boolean isDone) {
        this.description = task;
        this.isDone = isDone;
    }

    public abstract String fileFormat();
}