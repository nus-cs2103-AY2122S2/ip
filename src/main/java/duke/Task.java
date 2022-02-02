package duke;

public class Task {
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
        // mark done task with X
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String marked = "[" + (this.getStatusIcon()) + "]";
        String output = marked + " " + this.getDescription();
        return output;
    }
}