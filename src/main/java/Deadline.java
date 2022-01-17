/**
 * Represent the deadline of task.
 */

public class Deadline extends Task {
    protected String by;

    /**
     * Constructor for Deadline.
     *
     * @param description description of the task.
     * @param by by when the deadline end.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * String representation of Deadline.
     *
     * @return [D] with the status and description of the task,
     *         and by when.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

