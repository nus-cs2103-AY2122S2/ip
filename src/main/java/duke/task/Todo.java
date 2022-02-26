package duke.task;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {

    /**
     * Creates A Todo object with details of the task.
     *
     * @param description Description of the todo task.
     */
    public Todo(String description) {
        super(description, "T", null);
        assert description != null : "Description of todo cannot be null.";
        assert description.length() > 0 : "Body of todo cannot be empty.";
    }

    /**
     * Prints out the details of the Todo task.
     *
     * @return A string with details of the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
