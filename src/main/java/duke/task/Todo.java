package duke.task;

/**
 * A type of Task.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * @return String representation of a todo Task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
