package duke.task;

/**
 * Represents a todo task that needs to be done.
 */
public class ToDo extends Task {
    /**
     * Class constructor.
     *
     * @param description description of this task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of this task formatted as the way it is to be stored in disk.
     *
     * @return the string representation of this task formatted as the way it is to be stored in disk.
     */
    @Override
    public String toFileFormat() {
        return "T," + super.toFileFormat() + ",," + getStatusIcon();
    }

    /**
     * Returns the string representation of this task.
     *
     * @return the string representation of this task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
