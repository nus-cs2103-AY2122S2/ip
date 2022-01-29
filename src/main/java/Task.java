package duke;
/**
 * Represents the tasks added to the task list
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public static void main(String[] args) {
    }

    /**
     * Creates a new task with the given description
     * Default value of the isDone is false.
     * @param description describes the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * To retrieve the status of the task
     * @return status of the task, where X represents done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Changes the value of isDone to be true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Changes the value of isDone to be false.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * To display the task to the user and data log.
     * @return prints the task in the specified format
     */
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}