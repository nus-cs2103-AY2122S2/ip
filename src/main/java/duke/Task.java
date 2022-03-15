package duke;

/**
 * Represents a task.
 *
 * @author sibinhho99-nus
 */
public class Task {
    private boolean isDone;
    private String name;

    /**
     * Constructor for Task class.
     * @param isDone whether the task is done.
     * @param name the name of the task.
     */
    public Task(boolean isDone, String name) {
        this.isDone = isDone;
        this.name = name;
    }

    /**
     * Returns whether the current task is done.
     * @return whether the current task is done.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Marks the current task as done or not done.
     * @param isDone the state we wish to set.
     * @return whether the current task is done.
     */
    public boolean mark(boolean isDone) {
        this.isDone = isDone;
        return this.isDone;
    }

    /**
     * Returns name of the current task.
     * @return name of the current task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns String representation of the Task.
     *
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.isDone() ? "X" : " ", this.getName());
    }
}
