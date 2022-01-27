package duke.task;

/**
 * Represents a task. A <code>Task</code> object is represented by its name and a boolean indicating whether it is done.
 */
public abstract class Task {
    private final String name;
    private boolean done;

    /**
     * Returns a new <code>Task</code> object with the specified name and done status.
     * @param name Name of the task.
     * @param done Done status of the task.
     */
    public Task(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    /**
     * Returns a new <code>Task</code> object with the specified name that is not yet done.
     * @param name Name of the task.
     */
    public Task(String name) {
        this(name, false);
    }

    /**
     * Reverses the done status of the <code>Task</code>.
     */
    public void mark() {
        this.done = !this.done;
    }

    /**
     * Returns a String representation of the task.
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[" + (this.done? "X" : " ") + "] " + this.name;
    }

    /**
     * Returns the done status of the task.
     * @return Done status of the task.
     */
    public boolean getDone() {
        return this.done;
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
