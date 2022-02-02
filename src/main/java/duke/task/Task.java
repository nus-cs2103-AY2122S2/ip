package duke.task;

public abstract class Task {
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

    public abstract String getType();

    public String formatToSave() {
        return this.getType() + "|" + (this.getIsDone() ? "1" : "0") + "|" + this.description;
    };

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done duke.task with X
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }
}