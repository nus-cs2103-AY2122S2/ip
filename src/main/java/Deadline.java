/**
 * Tasks that is required to be done before a specific
 * date/time. Inherits from Task.
 */
public class Deadline extends Task {

    /**
     * String representation of the deadline.
     */
    protected String by;

    /**
     * Constructor to create a deadline task.
     *
     * @param description text description of the deadline.
     * @param by date/time deadline is due.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Outputs the string to represent deadline with
     * description and due date/time
     *
     * @return formatted string respresting the Deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}