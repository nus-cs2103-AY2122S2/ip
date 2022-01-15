public class Deadline extends Task {

    /** Deadline timing. */
    private String by;

    /**
     * Constructor for Deadline Class.
     * @param description The description of the deadline.
     * @param by The timing of the deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the task in proper format.
     * @return String of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
