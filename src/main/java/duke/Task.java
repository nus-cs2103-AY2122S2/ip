package duke;

public class Task {
    /**
     * String describing the task.
     */
    protected String description;

    /**
     * Boolean value, is true if the task is marked as done.
     */
    protected boolean isDone;

    /**
     * Constructor for a Task.
     *
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : "  "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void makeDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void makeNotDone() {
        this.isDone = false;
    }

    /**
     * {@inheritDoc}
     *
     * @return the String representation of the Task.
     */
    public String toString() {
        String out = String.format("[%s] %s", this.getStatusIcon(), this.description);
        return out;
    }
}
