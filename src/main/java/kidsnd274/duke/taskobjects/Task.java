package kidsnd274.duke.taskobjects;

public class Task {
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

    public Types getType() {
        return Types.TASK;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDetails() {
        return "";
    }

    public boolean isDone() {
        return isDone;
    }
}

