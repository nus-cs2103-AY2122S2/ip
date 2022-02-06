package bob;

/**
 * Represents a task to be done.
 */
public class ToDo extends bob.Task {
    /**
     * Constructor for a ToDo object, given description.
     *
     * @param description Description of the ToDo instance.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructor for a ToDo object, given description and status.
     *
     * @param description Description of the ToDo instance.
     * @param isDone      Status of the ToDo instance.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Generates a string representing this Task object for saving.
     *
     * @return A string representing this Task for saving.
     */
    @Override
    public String generateSavedEntry() {
        return String.format("T|%s|%s", isDone ? "1" : "0", description);
    }

    /**
     * Returns a string representation of this Task for display.
     *
     * @return A string representing this task for display.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
