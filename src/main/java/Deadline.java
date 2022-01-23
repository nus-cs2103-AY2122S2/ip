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

    /**
     * Returns the representative string for saving in data file.
     */
    @Override
    public String toFileFormat() {
        int status = super.isDone ? 1 : 0;
        return "E" + " | " + status + " | " + super.description + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
