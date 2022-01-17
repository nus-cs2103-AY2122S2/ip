public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Initializes a new instance of task, according to the task description.
     * By default, the new task is set to "not done".
     *
     * @param description Describes what needs to be done in this task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieves the status icon corresponding to the current status (isDone).
     *
     * @return "X" if task is already done, " " if task is not done.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Retrieves the task description.
     *
     * @return A string that represents the task description.
     */
    public String getDescription() {
        return this.description;
    }
}
