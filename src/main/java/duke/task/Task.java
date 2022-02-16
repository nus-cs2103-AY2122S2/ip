package duke.task;

/**
 * Encapsulates a task to be recorded by Duke.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Initialises a task.
     *
     * @param description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Checks if this task is done.
     *
     * @return true iff this task is done.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the description of this task.
     *
     * @return the description of this task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }


    /**
     * Marks this task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    private String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }


    /**
     * Returns the string representation of this task.
     *
     * @return the string representation of this task.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[")
                .append(this.getStatusIcon())
                .append("] ")
                .append(this.description);
        return sb.toString();
    }
}
