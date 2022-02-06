package duke.task;
/**
 * Represents a task object.
 */
public class Task {

    private String name;
    private Boolean status;

    /**
     * Constructor for the Task object.
     *
     * @param description The title of the task.
     */
    public Task(String description) {
        this.name = description;
        this.status = false;
    }

    /**
     * Marks a task as complete.
     */
    public void markTask() {
        this.status = true;
    }

    /**
     * Marks a task as incomplete.
     */
    public void unmarkTask() {
        this.status = false;
    }

    /**
     * Gets the title of a task.
     *
     * @return The title of the task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the status of a task, whether it is completed
     * or incomplete.
     *
     * @return A boolean value, true if complete, false if
     * incomplete.
     */
    public Boolean getStatus() {
        return this.status;
    }

    /**
     * Gets the symbol representing the task completeness status.
     *
     * @return [X] if task is complete, [ ] if task is
     * incomplete.
     */
    public String getSymbol() {
        return (this.status) ? "[X]" : "[ ]";
    }

    /**
     * Returns a string description of a task object.
     *
     * @return A string description of the task.
     */
    @Override
    public String toString() {
        return this.name;
    }

    /**
     * Formats a Task object to text.
     *
     * @return A text with information regarding the Task object.
     */
    public String formatText() {
        return "Text formatted";
    }

    /**
     * Returns boolean value based on completeness status of task.
     *
     * @return True if task is already marked as completed, false if
     * task is not yet marked as complete.
     */
    public boolean isMarked() {
        return this.status;
    }

}
