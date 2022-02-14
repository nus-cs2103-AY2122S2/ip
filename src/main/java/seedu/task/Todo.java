package seedu.task;

/**
 * The todo task
 */
public class Todo extends Task {

    private static final String TYPE = "T";

    public Todo(String description) {
        super(description, TYPE);
    }

    public Todo(String description, boolean isCompleted, int priority) {
        super(description, isCompleted, priority, TYPE);
    }
}
