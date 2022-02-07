package aeromon.task;

/**
 * ToDo task handles the ToDo task type.
 */
public class ToDo extends Task {

    /**
     * Constructs the ToDo object.
     * @param description the task name.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String toOutputFormat() {
        return String.format("T / %s", super.toOutputFormat());
    }
}
