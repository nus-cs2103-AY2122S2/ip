package bobby.task;

/**
 * Represents the ToDo object.
 */
public class ToDo extends Task {

    /**
     * Constructor class for ToDo task.
     *
     * @param taskName The name of the task.
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

