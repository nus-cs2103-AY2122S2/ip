package duke.tasks;

/**
 * Parent Group of task that is added to list
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor to initialise this task.
     *
     * @param description details of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Obtain answer on whether this task is or is not completed.
     *
     * @return X if completed and empty if not completed
     */
    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    /**
     * Mark the task as completed.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Mark task as not completed.
     */
    public void unMark() {
        isDone = false;
    }

    /**
     * Obtain the description of the task.
     *
     * @return a string value of the description of task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Obtain the unformatted data of this task for storage purposes.
     *
     * @return unformatted form of the task
     */
    public String getTaskData() {
        return "[" + getStatus() +"] " + description;
    }

    /**
     * Obtain the formatted data of this task for response purposes.
     *
     * @return formated form of the task
     */
    public String toString() {
        return "[" + getStatus() +"] " + description;
    }
}
