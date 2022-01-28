package task;

/**
 * Represents task which is entered with Todo Prefix.
 */
public class Todo extends Task {

    /**
     * Creates a Todo task.
     *
     * @param description Description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation with details
     * on the task type, mark status and description.
     *
     * @return String representation of Task to display.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string representation with details
     * on the task type, mark status and description.
     *
     * @return String representation of Task to save.
     */
    @Override
    public String toSave() {
        return "T" + super.toSave();
    }
}