package duke.task;

/**
 * Represents a basic task with a description.
 */
public class Todo extends Task {

    /**
     * Creates a Todo.
     *
     * @param description The description of the Todo.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
