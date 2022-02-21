package duke;

/**
 * The Todo class.
 *
 * @author Jet Tan
 */
public class Todo extends Task {
    /**
     * Constructor for a new instance of Todo, containing the description of the Todo.
     *
     * @param description The description of the Todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string containing task type, done status and description.
     *
     * @return string containing task type, done status and description
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
