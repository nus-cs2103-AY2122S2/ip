package ultoi.task;

/**
 * Represents a to-do task.
 *
 * @author snoidetx
 * @version 0.0.0
 */
public class ToDo extends Task {
    /**
     * Creates a to-do task.
     *
     * @param description Description of the to-do task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the standard input message to create this task.
     *
     * @return Input string.
     */
    public String toInputString() {
        return "todo " + description;
    }

    /**
     * Returns a string representation of the to-do task.
     *
     * @return String representation of the to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
