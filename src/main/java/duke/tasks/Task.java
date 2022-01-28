package duke.tasks;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public abstract String formatString();

    @Override
    public String toString() {
        if (this.getStatusIcon().equals("X")) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }
}
