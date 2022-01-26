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

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
