package task;

/**
 * Represents Task which is entered with Deadline Prefix.
 */
public class Deadline extends Task {
    /**
     * Deadline date.
     */
    protected String by;

    /**
     * Creates a Deadline task.
     *
     * @param description Description of the Deadline task.
     * @param by Deadline Date.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation with details
     * on the task type, mark status, description and
     * deadline date.
     *
     * @return String representation of Task to display.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns the string representation with details
     * on the task type, mark status, description and
     * deadline date.
     *
     * @return String representation of Task to save.
     */
    @Override
    public String toSave() {
        return "D" + super.toSave() + " : " + by;
    }
}
