public class Task {
    private String description;
    private Boolean isDone;

    /**
     * Constructor for the task object.
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Mark the current task as completed.
     */
    public void markAsCompleted() {
        this.isDone = true;
    }

    /**
     * Mark the current task as uncompleted.
     */
    public void markAsUncompleted() {
        this.isDone = false;
    }

    /**
     * Get the string representation of the task status.
     *
     * @return 'x' or ' ' depending on the status
     */
    public String getStatusIcon() {
        return isDone ? "x" : " ";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }
}
