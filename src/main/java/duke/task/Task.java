package duke.task;

import java.io.Serializable;

/**
 * Represents a task with description and if the task is done.
 */
public class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task with description and task not done.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns status icon of task.
     *
     * @return the string representation of status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /** Change task to done */
    public void markAsDone() {
        this.isDone = true;
    }

    /** Change task to not done */
    public void unMark() {
        this.isDone = false;
    }

    /**
     * @param newDescription Updates Task description to newDescription.
     */
    public void updateDescription(String newDescription) {
        this.description = newDescription;
    }

    /**
     * Returns formatted string representation of task.
     *
     * @return String representation of task.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
