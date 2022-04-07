package duke.task;

/**
 * Represents a todo task.
 */
public class Todo extends Task {

    /**
     * Create a todo task.
     *
     * @param description Description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns formatted string representation of todo task.
     *
     * @return String representation of todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
