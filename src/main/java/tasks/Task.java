package tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() { return (isDone ? "X" : " "); }

    public String getStatusNumber() { return (isDone ? "1" : "0"); }

    public void markIsDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public String toFileFormat() {
        return "," + getStatusNumber() + "," + this.description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }




}

