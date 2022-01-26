package duke;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String toStringForSave() {
        return (isDone ? "# 1 # " : "# 0 # ") + this.description;
    }

    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + this.description;
    }

}
