package seedu.task;

/**
 * The todo task
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isCompleted, int priority) {
        super(description, isCompleted, priority);
    }

    @Override
    public String toFile() {
        return "T\t" + super.toFile();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
