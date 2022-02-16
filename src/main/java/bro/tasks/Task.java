package bro.tasks;

import java.time.LocalDateTime;

/**
 * Represents a general task created by the user.
 *
 * @see Deadline
 * @see Event
 * @see Todo
 */
public abstract class Task {
    protected String taskName;
    protected char done = ' ';

    public Task() { }

    /**
     * Marks this task as done. Does nothing if this task is already marked.
     *
     * @return True if the task is successfully marked, false if it is already marked.
     */
    public boolean markDone() {
        if (this.done == ' ') {
            this.done = 'X';
            return true;
        }
        return false;
    }

    /**
     * Marks this task as not done. Does nothing if this task is already unmarked.
     *
     * @return True if the task is successfully unmarked, false if it is already unmarked.
     */
    public boolean markUndone() {
        if (this.done == 'X') {
            this.done = ' ';
            return true;
        }
        return false;
    }

    /**
     * Checks whether a task is marked as done or not.
     *
     * @return 'X' if it is done, ' ' otherwise.
     */
    public char getDone() {
        return this.done;
    }

    /**
     * Gets the name of this Task.
     *
     * @return The name of the Task.
     */
    public String getTaskName() {
        return this.taskName;
    }
    public abstract String getDate();
    public abstract LocalDateTime getDateObj();

    /**
     * Returns a String representation of the Task.
     *
     * @return The String representation of the Task.
     */
    public String toString() {
        return String.format("[%c][%c] %s", this.getType(), this.done, this.taskName);
    }

    public char getType() {
        return ' ';
    }

    /**
     * Updates the name of this task to a new name. Does nothing if the new name is the same as the current name.
     *
     * @param newName The new name of this task.
     * @return True if the Task is successfully renamed, false otherwise.
     */
    public boolean updateName(String newName) {
        if (this.taskName.equals(newName.strip())) {
            /* New Task name is the same, update not needed */
            return false;
        }

        this.taskName = newName.strip();
        return true;
    }

    /**
     * Updates the date of this task to a new date. Does nothing if the new date is the same as the current date.
     *
     * @param newDate The new date string to replace the current one with.
     * @see Task#updateDate(LocalDateTime)
     */
    public boolean updateDate(String newDate) {
        return false;
    }

    /**
     * Updates the date of this task to a new date. Does nothing if the new date is the same as the current date.
     *
     * @param newDate The new date object to replace the current one with.
     * @see Task#updateDate(String)
     */
    public boolean updateDate(LocalDateTime newDate) {
        return false;
    }
}

