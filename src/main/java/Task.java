/**
 * Encapsulate information of a user task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task objects.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieve the task status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Change status of task to be 'Done'.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Change status of task to be 'Not done'.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the representative string for saving in data file.
     */
    public String toFileFormat() {
        return "";
    }

    /**
     * Format the string representation of Task objects.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
