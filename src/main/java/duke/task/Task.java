package duke.task;

import java.time.LocalDate;

/**
 * The Task class handles the interaction and form of tasks.
 *
 * @author Rdac0
 */
public class Task {
    private String name;
    private boolean isDone;

    /**
     * Creates a Task object.
     *
     * @param text The name of the Task.
     */
    public Task(String text) {
        this.name = text;
        this.isDone = false;
    }

    /**
     * Returns null. Only here to be inherited.
     * Should never be called.
     *
     * @return null.
     */
    public LocalDate getTime() {
        return null;
    }

    public String getName() {
        return name;
    }

    public boolean getDone() {
        return isDone;
    }

    /**
     * Sets the task as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Sets the task as undone.
     */
    public void setUndone() {
        this.isDone = false;
    }

    /**
     * Checks if specified keyword is in the Task name.
     *
     * @param text Specifying keyword.
     * @return True if keyword found, false otherwise.
     */
    public boolean isFoundInName(String text) {
        if (name.contains(text)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns a formatted String representation of this Task.
     *
     * @return Formatted String.
     */
    @Override
    public String toString() {
        String mark;
        if (this.isDone) {
            mark = "[X] ";
        } else {
            mark = "[ ] ";
        }
        return "[T]" + mark + this.name;
    }
}
