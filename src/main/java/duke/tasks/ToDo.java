package duke.tasks;

import duke.exceptions.InvalidOperationException;

public class ToDo extends Task {
    private String description;
    private boolean done;
    private String line = "-------------------------------------------";

    public ToDo(String details) {
        this.description = details;

    }

    @Override
    public void mark() throws InvalidOperationException {
        if (this.done) {
            throw new InvalidOperationException("marked");
        }
        this.done = true;

    }

    @Override
    public void unmark() throws InvalidOperationException {
        if (!this.done) {
            throw new InvalidOperationException("unmarked");
        }
        this.done = false;

    }

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
