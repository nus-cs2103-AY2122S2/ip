public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a Deadline class for tasks that needs to be done before a timing
     * @param description String
     * @param by String, the deadline to meet by
     */
    Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by);
    }
}
