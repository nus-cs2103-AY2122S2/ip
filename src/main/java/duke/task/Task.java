package duke.task;

import duke.exception.InvalidActionException;

/** Superclass of concrete classes that can be added to the task list */
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

    /**
     * Returns a String representation of the Task.
     * Indicates whether it has been done.
     *
     * @return A String representation of the Task.
     */
    @Override
    public String toString() {
        String status = isDone ? "X" : " ";
        return "[" + status + "] " + name;
    }

    /**
     * Marks self as done.
     *
     * @throws InvalidActionException If already done.
     */
    public void markAsDone() throws InvalidActionException {
        if (isDone) {
            throw new InvalidActionException("Task already done!");
        }
        isDone = true;
    }

    /**
     * Marks self as undone.
     *
     * @throws InvalidActionException If already undone.
     */
    public void markUndone() throws InvalidActionException {
        if (!isDone) {
            throw new InvalidActionException("Task already not done!");
        }
        isDone = false;
    }

    /**
     * Returns a string representation of the task to be entered in the storage file.
     *
     * @return A String representation of the task.
     */
    public abstract String convertToFileFormat();
}
