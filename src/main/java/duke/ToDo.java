package duke;

/**
 * Inherits from the Task class.
 * Stores the description of a ToDo object.
 * Provides to String method to print event details in specific format.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}