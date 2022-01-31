package duke.tasks;

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

    public boolean markDone() {
        if (this.done == ' ') {
            this.done = 'X';
            return true;
        }
         return false;
    }
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
    public char getDone(){
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
}

