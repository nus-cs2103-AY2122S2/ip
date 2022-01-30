package duke.Task;

/**
 * A class that store the information about a task
 */
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
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public String markAsDone() {
        this.isDone = true;
        return this.toString();
    }

    public String markAsUndone() {
        this.isDone = false;
        return this.toString();
    }

    public boolean isDone() {
        return isDone;
    }

    public String dataFormatOfTask() {
        return null;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + this.description;
    }

}