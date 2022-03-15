package duke.task;

import java.io.Serializable;

/**
 * Abstract class representing a task with a description.
 */
public abstract class Task implements Serializable {

    protected String description;
    protected boolean isDone = false;

    Task(String description) {
        this.description = description;
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks task i.e. mark task as not completed.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Represents whether the task is completed or not.
     * @return "X" if completed, " " if not completed.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public boolean descriptionContains(String keyword) {
        return this.description.contains(keyword);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
