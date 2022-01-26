public class Deadline extends Task {
    private final String by;

    /**
     * Public constructor for the Deadline object.
     * @param description the task name
     * @param by the deadline which the task needs to be completed by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%1$s (by: %2$s)", super.toString(), this.by);
    }

    @Override
    public String toOutputFormat() {
        return String.format("D / %1$s / %2$s", super.toOutputFormat(), this.by);
    }
}
