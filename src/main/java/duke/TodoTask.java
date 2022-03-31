package duke;

/**
 * Represents task of todo type.
 *
 * @author sibinhho99-nus
 */
public class TodoTask extends Task {
    /**
     * Constructor for task of todo type.
     * @param isDone whether the task is done.
     * @param name the name of the task.
     */
    public TodoTask(boolean isDone, String name) {
        super(isDone, name);
    }

    /**
     * Returns String representation of the Task.
     */
    @Override
    public String toString() {
        return String.format("[T][%s] %s", this.isDone() ? "X" : " ", this.getName());
    }
}
