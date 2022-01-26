package duke;

public class Task {

    /** The description of the task. */
    private String description;

    /** The completion status of a task. */
    private boolean isDone;

    /**
     * Constructor of Task.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a cross if task has been completed.
     * @return String of completion status.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Sets the isDone to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets the isDone to false.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    public String writeToFile() {
        return this.isDone ? "| 1 | " + this.description : "| 0 | " + this.description;
    }
    /**
     * Returns task in proper format.
     * @return String of the Task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
