package duke.task;

/**
 * Returns an object that encapsulates the details which the user wishes to complete.
 */
public class Task {

    private String task;
    private boolean isCompleted;

    /**
     * Returns a new initialized task.
     *
     * @param task Task name.
     * @param isCompleted Status of the task.
     */
    public Task(String task, boolean isCompleted) {
        this.task = task;
        this.isCompleted = isCompleted;
    }

    /**
     * Returns the task as completed.
     */
    public void mark() {
        this.isCompleted = true;
    }

    /**
     * Returns the task as incomplete.
     */
    public void unmark() {
        this.isCompleted = false;
    }

    /**
     * Returns a boolean depending on whether the task is completed.
     *
     * @return A boolean on whether the task is completed.
     */
    public boolean isCompleted() {
        return this.isCompleted;
    }

    /**
     * Returns the task name.
     *
     * @return A string representing the task name.
     */
    public String getTaskName() {
        return this.task;
    }

    /**
     * Returns the task in a readable form.
     *
     * @return A string representing the details of the task.
     */
    @Override
    public String toString() {
        StringBuilder status = new StringBuilder();
        if (this.isCompleted) {
            status.append("[X] ");
        } else {
            status.append("[ ] ");
        }
        return status.append(this.task).toString();
    }
}
