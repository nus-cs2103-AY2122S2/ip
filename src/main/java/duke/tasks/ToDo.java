package duke.tasks;

import java.io.Serializable;

import duke.exceptions.InvalidOperationException;

/**
 * Task Object of type ToDo.
 * A ToDo Object can hold a String description and a boolean isDone.
 */
public class ToDo extends Task implements Serializable {
    private String description;
    private boolean isDone;

    /**
     * Constructs the ToDo Object.
     *
     * @param details Description of the task
     */
    public ToDo(String details) {
        this.description = details;
    }

    /**
     * Marks the boolean isDone as true.
     *
     * @throws InvalidOperationException if isDone is already true
     */
    @Override
    public void mark() throws InvalidOperationException {
        if (this.isDone) {
            throw new InvalidOperationException("marked");
        }
        this.isDone = true;
    }

    /**
     * Marks the boolean isDone as false.
     *
     * @throws InvalidOperationException if isDone is already false
     */
    @Override
    public void unmark() throws InvalidOperationException {
        if (!this.isDone) {
            throw new InvalidOperationException("unmarked");
        }
        this.isDone = false;
    }

    @Override
    public void update(String description) {
        this.description = description;
    }

    /**
     * @return a String representation of the ToDo Object
     */
    @Override
    public String toString() {
        if (isDone) {
            return String.format("[T][X] %s", this.description);
        } else {
            return String.format("[T][ ] %s", this.description);
        }
    }
}
