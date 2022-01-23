package Tasks.TaskTypes;

public class Deadline extends Task {
    protected String by;

    /**
     * Creates a new Deadline.
     *
     * @param name
     * @param by
     */
    public Deadline (String name, String by) {
        super(name);
        this.by = by;
    }

    /**
     * Generates a new Deadline and initialises it with the
     * given completion status.
     *
     * @param isDone
     * @param title
     * @param by
     */
    public Deadline (boolean isDone, String title, String by) {
        super(isDone, title);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }

    @Override
    public String encodeTask() {
        return String.format("D @@@ %b @@@ %s @@@ %s", super.isDone(), super.getTitle(), this.by);
    }
}
