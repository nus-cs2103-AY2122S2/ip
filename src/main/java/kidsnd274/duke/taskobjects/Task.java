package kidsnd274.duke.taskobjects;

public abstract class Task {
    private String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return this.taskName;
    }

    public String getStatusIcon() {
        return (isDone ? "x" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String getCurrentStatus() {
        return "[" + getStatusIcon() + "] " + this;
    }
    public abstract Types getType();

    public String getTaskName() {
        return taskName;
    }

    public boolean isDone() {
        return isDone;
    }
}

