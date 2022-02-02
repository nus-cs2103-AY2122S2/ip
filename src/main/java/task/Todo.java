package task;

/**
 * Represent a todo, a type of task.
 * @author Fan Jue
 * @version 0.1.0
 * @since 0.1.0
 */
public class Todo extends Task {
    /**
     * Create a todo.
     *
     * @param description A description of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Convert the todo to a string in the format of storage memory.
     *
     * @return Memory representation of the todo.
     */
    @Override
    public String toMemoryString() {
        return "T" + super.toMemoryString();
    }

    /**
     * Convert the todo to a string for UI.
     *
     * @return The UI representation of the todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
