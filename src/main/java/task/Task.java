package task;

public abstract class Task {
    public String description;
    public boolean done;

    public Task (String task, boolean done) {
        this.description = task;
        this.done = done;
    }

    public abstract String fileFormat();
}