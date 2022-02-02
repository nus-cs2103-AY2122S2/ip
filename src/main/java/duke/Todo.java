package duke;

/**
 * Inherits from the Task class.
 * Stores the description of a Todo object.
 * Provides to String method to print event details in specific format.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}