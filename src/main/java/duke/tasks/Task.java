package duke.tasks;

import java.io.Serializable;

/**
 * Represents a abstract task.
 * Stores the description and a boolean which signifies if a task
 * is completed.
 */
public abstract class Task implements Serializable {

    protected String description;
    protected boolean isCompleted;

    /**
     * Constructor of a task object, to be inherited and used
     * by concrete subclasses of the Task class.
     *
     * @param description the details of the task.
     */
    public Task (String description) {
        this.description = description;
        isCompleted = false;
    }

    /**
     * Sets the completion status of a task to true/false.
     *
     * @param value the boolean which represents the task's new completion status.
     */
    protected void setCompleted(boolean value) {
        isCompleted = value;
    }

    public String toString() {
        String display = isCompleted ? "[X]" : "[ ]";
        return display + " " + description.toString();
    }


}