package kidsnd274.duke.taskobjects;

public abstract class Task {
    private String taskName;
    private boolean done;

    public Task(String taskName) {
        this.taskName = taskName;
        this.done = false;
    }

    @Override
    public String toString() {
        return this.taskName;
    }

    public String getStatusIcon() {
        return (done ? "x" : " ");
    }

    public void markAsDone() {
        this.done = true;
    }

    public void markAsUndone() {
        this.done = false;
    }

    public String getCurrentStatus() {
        return "[" + getStatusIcon() + "] " + this;
    }

    public abstract String getType();
}

