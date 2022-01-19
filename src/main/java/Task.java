public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of this Task
     *
     * @return "X" if this Task is done, else returns " "
     */
    public String getStatusIcon() {
        // mark done task with X
        return (isDone ? "X" : " ");
    }

    /**
     * Marks this Task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks this Task as not done
     */
    public void markAsNotDone() {
        this.isDone = false;
    }
}