package tasks;

public class Deadline extends Task {
    protected String by;

    /**
     * Constructor for the deadline object.
     *
     * @param description
     * @param by the deadline for the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
