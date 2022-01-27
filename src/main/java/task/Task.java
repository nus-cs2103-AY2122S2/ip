package task;

import java.io.Serializable;

/**
 * Represents an abstraction of sub-types.
 * A task is a parent to the different kinds of task
 * a user can create - todo/deadline/event.
 */
public abstract class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    /**
     * Class constructor.
     * Sets the task's isDone to false.
     *
     * @param description Description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task completed.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task incomplete.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns the status of the task.
     * X - done
     * Empty string - incomplete
     * @return Task's status
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done java.task with X
    }

    /**
     * Returns task's basic details.
     *
     * @return Output string to indicate task's basic details.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}