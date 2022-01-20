public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Public constructor for the Task object.
     * @param description the task name
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Public method which returns the status icon of the respective task.
     * @return "X" if the task is done, " " otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Public void method that marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Public void method that marks the task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%1$s] %2$s", getStatusIcon(), description);
    }
}