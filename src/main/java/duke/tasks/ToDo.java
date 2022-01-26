package duke.tasks;

import duke.exceptions.InvalidOperationException;

public class ToDo extends Task {
    private String description;
    private boolean isDone;

    public ToDo(String details) {
        this.description = details;
    }

    @Override
    public void mark() throws InvalidOperationException {
        if (this.isDone) {
            throw new InvalidOperationException("marked");
        }
        this.isDone = true;
    }

    @Override
    public void unmark() throws InvalidOperationException {
        if (!this.isDone) {
            throw new InvalidOperationException("unmarked");
        }
        this.isDone = false;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[T]"+"[X] " + this.description;
        }
        else {
            return "[T]"+"[ ] "+ this.description;
        }
    }
}
