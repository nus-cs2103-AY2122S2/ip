package duke;

/**
 * A type of Task that represents a task that a user will complete in the near future.
 * But the completion date is not specified.
 */
public class ToDos extends Task {
    public ToDos (String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + "[" + this.getStatusIcon() + "]" + description;
    }
}
