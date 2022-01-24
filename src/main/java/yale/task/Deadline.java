package yale.task;

/**
 * Subclass of Task
 * Added modification of generic
 * Task object
 */
public class Deadline extends Task {
    /**
     * String to represent the date
     * and time of deadline
     */
    protected String by;

    /**
     * Constructor method
     * @param name
     * @param isMarked
     * @param by
     */
    public Deadline(String name, boolean isMarked, String by) {
        super(name, isMarked);
        this.by = by;
    }
    /**
     * Returns a customised String
     * @return Customised String format
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
