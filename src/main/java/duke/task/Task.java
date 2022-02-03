package duke.task;

/**
 * Represents a task. A <code>Task</code> object is represented by its name and a boolean indicating whether it is done.
 */
public abstract class Task {
    private final String name;
    private boolean isDone;

    /**
     * Returns a new <code>Task</code> object with the specified name and isDone status.
     * @param name Name of the task.
     * @param isDone isDone status of the task.
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Returns a new <code>Task</code> object with the specified name that is not yet done.
     * @param name Name of the task.
     */
    public Task(String name) {
        this(name, false);
    }

    /**
     * Reverses the isDone status of the <code>Task</code>.
     */
    public void mark() {
        this.isDone = !this.isDone;
    }

    /**
     * Returns a String representation of the task.
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.name;
    }

    /**
     * Returns the isDone status of the task.
     * @return isDone status of the task.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns the name of the task.
     * @return Name of the task.
     */
    public String getName() {
        return this.name;
    }

    public abstract String toText();
}
