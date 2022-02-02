package duke.task;

import duke.DukeException;

/**
 * Represents a generic task that is not of any specific type.
 */
public abstract class Task {
    protected final String description;
    protected boolean isDone = false;

    /**
     * Constructs a Task instance.
     *
     * @param description The description of the Task.
     */
    public Task(String description) {
        if (description == null || description.isEmpty()) {
            throw new DukeException("The description of a Task cannot be empty");
        }

        this.description = description;
    }

    /**
     * Constructs a Task instance.
     *
     * @param description The description of the Task.
     * @param isDone      Whether the Task is done or not.
     */
    public Task(String description, boolean isDone) {
        this(description);
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns a String that represents the state of the Task.
     *
     * @return The String representation of the Task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    protected String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Returns an easily parsable String that represents the state of the Task.
     *
     * @return The easy to parse String representation of the Task.
     */
    public abstract String toSaveData();
}
