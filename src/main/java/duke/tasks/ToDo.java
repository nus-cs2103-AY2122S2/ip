package duke.tasks;

import duke.exceptions.InvalidOperationException;

/**
 * Task Object of type ToDo.
 * A ToDo Object can hold a String description and a boolean isDone.
 */
public class ToDo extends Task {
    private String description;
    private boolean done;

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
        if (this.done) {
            throw new InvalidOperationException("marked");
        }
        this.done = true;

    }

    /**
     * Marks the boolean isDone as false.
     *
     * @throws InvalidOperationException if isDone is already false
     */
    @Override
    public void unmark() throws InvalidOperationException {
        if (!this.done) {
            throw new InvalidOperationException("unmarked");
        }
        this.done = false;

    }

    /**
     * @return a String representation of the ToDo Object
     */
    @Override
    public String toString() {
        if (done) {
            return "[T]"+"[X] " + this.description;
        }
        else {
            return "[T]"+"[ ] "+ this.description;
        }
    }

}
