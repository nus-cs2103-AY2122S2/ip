/**
 * Deadline task which inherits from Task class.
 */
public class Deadline extends Task {

    /** Deadline of task. */
    private String by;

    /**
     * Constructor for Deadline class.
     *
     * @param desc Description of deadline task.
     * @param by Deadline of deadline task.
     */
    public Deadline(String desc, String by) {
        super(desc);
        this.by = by;
    }

    /**
     * String representation of deadline task.
     *
     * @return String representation of deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
