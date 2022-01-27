package pikabot.task;

/**
 * Represents a Task that is a Todo.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo.
     *
     * @param description Description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Converts Todo to a formatted string.
     *
     * @return Formatted string representation of Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
