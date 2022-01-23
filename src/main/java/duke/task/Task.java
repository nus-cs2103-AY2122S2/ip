package duke.task;

/**
 * Object that encapsulates the details which the user wishes to complete.
 */
public class Task {

    private String task;
    private boolean completed;

    /**
     * Initializes a task.
     *
     * @param task Task name.
     * @param completed Status of the task.
     */
    public Task(String task, boolean completed) {
        this.task = task;
        this.completed = completed;
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        this.completed = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void unmark() {
        this.completed = false;
    }

    /**
     * Checks to see if the task is completed.
     *
     * @return A boolean on whether the task is completed.
     */
    public boolean isCompleted() {
        return this.completed;
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
     * Writes the task in a readable form.
     *
     * @return A string representing the details of the task.
     */
    @Override
    public String toString() {
        StringBuilder status = new StringBuilder();
        if (this.completed) {
            status.append("[X] ");
        } else {
            status.append("[ ] ");
        }
        return status.append(this.task).toString();
    }
}
