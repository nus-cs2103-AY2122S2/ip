/**
 * A class that creates Deadline objects with by variable,
 * that stores the deadline information for that task.
 */
public class Deadline extends Task {
    private final String by;
    private DateTime timings;

    public Deadline(String e, String by) {
        super(e);
        timings = new DateTime(by);
        this.by = timings.getString();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(By: " + this.by + ")";
    }
}
