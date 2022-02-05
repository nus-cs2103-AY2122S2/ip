package duke.task;

/**
 * A class that store the information about a task
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor.
     * @param description description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor when loading data.
     * @param description description
     */
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

    /**
     * Mark task as done.
     * @return the task description
     */
    public String markAsDone() {
        this.isDone = true;
        return this.toString();
    }
    /**
     * Mark task as undone.
     * @return the task description
     */
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
