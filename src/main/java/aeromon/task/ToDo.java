package aeromon.task;

/**
 * ToDo task handles the ToDo task type.
 */
public class ToDo extends Task {

    private static final String TODO_STRING_FORMAT = "[T]%s";
    private static final String TODO_OUTPUT_FORMAT = "T / %s";

    /**
     * Constructs the ToDo object.
     * @param description the task name.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return String.format(TODO_STRING_FORMAT, super.toString());
    }

    @Override
    public String toOutputFormat() {
        return String.format(TODO_OUTPUT_FORMAT, super.toOutputFormat());
    }
}
