package duke.task;

/**
 * Represents a task input from the user.
 */
public class Task {

    /** The description of the task */
    private String description;

    /** The completion status of a task */
    private boolean isDone;

    /**
     * Constructor of Task.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a cross if task has been completed.
     *
     * @return String of completion status.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Sets the completion status of the task to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets the completion status of task to false.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns a standardized format for storing the task into the data file.
     *
     * @return String of task in data file storage format.
     */
    public String writeToFile() {
        return this.isDone ? "| 1 | " + this.description : "| 0 | " + this.description;
    }

    /**
     * Adds extra custom formatting for user view.
     *
     * @return A custom String display of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
