package duke;

/**
 * Task class contains the task's name and whether it is completed
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Check if task is done
     * @return String "X" to indicate if a task is done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done with X
    }

    /**
     * Mark item as done
     * @return Item that is done
     */
    public Task markAsDone() {
        this.isDone = true;
        return this;
    }

    /**
     * Mark item as undone
     * @return Item that is undone
     */
    public Task unmarkItem() {
        this.isDone = false;
        return this;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
