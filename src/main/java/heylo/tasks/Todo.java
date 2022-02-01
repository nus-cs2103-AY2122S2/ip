package heylo.tasks;

/**
 * Represents a todo task.
 */
public class Todo extends Task {
    /**
     * Creates a todo task.
     *
     * @param description String todo-description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Converts the todo task to String format.
     *
     * @return String todo.
     */
    @Override
    public String toString() {
        return " [T]" + super.toString();
    }
}