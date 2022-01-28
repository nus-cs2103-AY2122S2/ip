package duke.task;

/**
 * Represents a task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public Task(String description) {
        this(description, false);
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns a string X indicating whether the task is done or not.
     *
     * @return String X indicating whether the task is done or not.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     *
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns the task as a string which can be saved and loaded as a task again.
     *
     * @return Task as a string which can be saved and loaded as a task again.
     */
    public String save() {
        int s = 0;
        if (isDone) {
            s = 1;
        }
        return s + " | " + description;
    }

    /**
     * Returns the task as a readable string.
     *
     * @return Task as a readable string.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
