package duke.commands;

/**
 * Represents a Task to be done by the user with no specified date.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo object.
     */
    public ToDo(String description) {
        super(description, "T");
    }

    /**
     * Returns a formatted String describing the Todo.
     *
     * @return formatted String describing the Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
