package duke.task;

/**
 * Task class that is inherited by Deadline,Event,Todo
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Default constructor for task
     * isDone is default false
     *
     * @param description Description for task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Overloaded constructor for task
     *
     * @param description Description for task
     * @param isDone Sets whether task is done
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets description of task
     *
     * @return String description of task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns icon for whether task is done
     * X for done, empty for not done
     *
     * @return X or Nothing
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Sets isDone to true
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets isDone to false
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Overloaded toString function
     * String with status icon and description
     *
     * @return returns task as a string
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Empty getFileString function for child classes
     *
     * @return Nothing
     */
    public String getFileString() {
        return "";
    }
}
