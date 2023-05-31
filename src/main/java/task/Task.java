package task;

import java.io.Serializable;

import parser.Priorities;

/**
 * Represents an abstraction of sub-types.
 * A task is a parent to the different kinds of task
 * a user can create - todo/deadline/event.
 */
public abstract class Task implements Serializable, Comparable {
    protected String description;
    protected boolean isDone;
    protected Priorities priority;

    /**
     * Class constructor.
     * Sets the task's isDone to false.
     *
     * @param description Description of task
     */
    public Task(String description, Priorities priority) {
        this.description = description;
        this.isDone = false;
        this.priority = priority;
    }


    /**
     * Gets the task's description.
     *
     * @return task's description
     */
    public String getDescription() {
        return this.description;
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
        return (isDone ? "X" : " ");
    } // mark done java.task with X

    /**
     * Returns task's basic details.
     *
     * @return Output string to indicate task's basic details.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Compares the priority of the task to sort the list out.
     *
     * @param o Task to compare with
     * @return integer to indicate which task takes precedence
     */
    @Override
    public int compareTo(Object o) {
        if (o instanceof Task) {
            Task other = (Task) o;
            return this.priority.compareTo(other.priority);
        } else {
            return 0;
        }
    }
}
