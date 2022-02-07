package duke;

/**
 * Represents a task with no date attached
 */
public class ToDo extends Task {
    ToDo(String description) {
        super(description);
    }

    ToDo(String description, Boolean isCompleted) {
        super(description, isCompleted);
    }

    /**
     * Returns the type of <code>Task</code>
     * @return String representing the type of <code>Task</code>
     */
    @Override
    public String getType() {
        return "ToDo";
    }

    /**
     * Obtains the string representation of the <code>ToDo</code> object
     * @return String corresponding to the <code>ToDo</code> object
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
