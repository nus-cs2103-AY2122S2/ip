/**
 * Represents a task that needs to be done before a specific date/time.
 * Includes a String representation of the date/time.
 *
 * @author Abdulelah Faisal S Al Ghrairy
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructor for Deadline class
     * @param d a string representing a description of the task
     * @param b a string representing the date/time
     */
    public Deadline(String d, String b) {
        super(d);
        this.by = b;
    }

    @Override
    /**
     * Returns the task properties in the format of the task to be saved onto hard disk
     * @return String representing the task toString in hard-disk format
     */
    public String toStringInFileFormat() {
        return "D|" + this.getStatusIcon() + "|" + this.description + "|" + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
