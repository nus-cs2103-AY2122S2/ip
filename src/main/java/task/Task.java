package task;

import main.Date;

/**
 * Represent a task, comprising of event, todo, deadline.
 * @author Fan Jue
 * @version 0.1.0
 * @since 0.1.0
 */
public abstract class Task {
    /** A string to represent the description of the task */
    protected String description;
    /** A boolean to represent if the task is done */
    protected boolean isDone;

    /**
     * Create a task.
     *
     * @param description A description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Return the status of the task.
     *
     * @return "X" if the task is done, " " otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Mark the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Mark the task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Check if the task occurs/ends on a specific date.
     *
     * @param date An input date to check with the instance.
     * @return True if the task occurs on the input date.
     */
    public boolean isOn(Date date) {
        return false;
    }

    /**
     * Convert the task to a string in the format of storage memory.
     *
     * @return Memory representation of the task.
     */
    public String toMemoryString() {
        if (isDone) {
            return "@1@" + this.description;
        } else {
            return "@0@" + this.description;
        }
    }

    /**
<<<<<<< HEAD
     * Return a boolean representing if the keyword in search matches the task description.
     *
     * @param keyword The keyword to filter tasks.
     * @return True if the keyword exists in the task description, false otherwise.
     */
    public boolean isMatch(String keyword) {
        return (!this.description.contains(keyword));
    }

    /**
     * Convert the task to a string for UI.
     *
     * @return The UI representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] "
                + this.description;
    }

}
