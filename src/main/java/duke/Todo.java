package duke;

/**
 * One of the three types of tasks that can be created.
 * Indicates a task with no frills
 */
public class Todo extends Task {

    /**
     * Constructor for a Todo.
     *
     * @param description description of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * {@inheritDoc}
     * @return the String representation of the Todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
