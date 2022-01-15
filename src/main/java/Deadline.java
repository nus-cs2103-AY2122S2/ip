/**
 * Encapsulate information of task with deadline.
 */
public class Deadline extends Task{
    protected String by;

    /**
     * Normal constructor.
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
