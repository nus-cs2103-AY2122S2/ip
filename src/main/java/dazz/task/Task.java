package dazz.task;

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
    public String getStatusIcon() { return (isDone ? "X" : " "); }

    public boolean getIsDone() { return this.isDone;}

    public String getDescription() { return this.description; }

    private boolean hasAlreadyMark() {
        return this.isDone;
    }

    private boolean hasAlreadyUnmark() {
        return !this.isDone;
    }

    public void setDone() { if (!hasAlreadyMark()) { this.isDone = true; } }

    public void setUndone() { if (!hasAlreadyUnmark()) { this.isDone = false; } }

    public String writeToFile() {
        int isDone = this.getIsDone() ? 1 : 0;
        return isDone + " === " + this.description;
    }

    @Override
    public String toString() { return "[" + this.getStatusIcon() + "] " + this.description; }
}