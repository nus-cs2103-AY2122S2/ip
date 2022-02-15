package duke.task;

/**
 * Represents a task to do.
 */
public class Todo extends Task {
    /**
     * Creates an instance of Todo.
     *
     * @param description of Todo.
     */
    public Todo(String description) {
        super(description, Type.TODO);
    }

    /**
     * Returns the string of todo.
     *
     * @return the string of todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
