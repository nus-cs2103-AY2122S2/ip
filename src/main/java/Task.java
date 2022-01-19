public class Task {
    private String value;
    private Boolean completed;

    /**
     * Constructor for the task object.
     *
     * @param value
     */
    public Task(String value) {
        this.value = value;
        this.completed = false;
    }

    /**
     * Mark the current task as completed.
     */
    public void markAsCompleted() {
        this.completed = true;
    }

    /**
     * Mark the current task as uncompleted.
     */
    public void markAsUncompleted() {
        this.completed = false;
    }

    /**
     * Get the string representation of the task status.
     *
     * @return 'x' or ' ' depending on the status
     */
    public String getStatusString() {
        return completed ? "x" : " ";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusString(), this.value);
    }
}
