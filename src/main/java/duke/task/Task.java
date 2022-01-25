package duke.task;

import duke.exception.InvalidActionException;

public abstract class Task {
    protected String name;
    protected boolean isDone;

    protected Task(String name) {
        this(name, false);
    }

    protected Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        String status = isDone ? "X" : " ";
        return "[" + status + "] " + name;
    }

    public void markAsDone() throws InvalidActionException {
        if (isDone) { throw new InvalidActionException("Task already done!"); }
        isDone = true;
    }

    public void markUndone() throws InvalidActionException {
        if (!isDone) { throw new InvalidActionException("Task already not done!"); }
        isDone = false;
    }

    public abstract String convertToFileFormat();
}
