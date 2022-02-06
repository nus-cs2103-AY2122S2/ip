package duke;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void  makeDone() {
        this.isDone = true;
    }

    public void makeNotDone() {
        this.isDone = false;
    }

    public String toString() {
        String out = String.format("[%s] %s", this.getStatusIcon(), this.description);
        return out;
    }
}
