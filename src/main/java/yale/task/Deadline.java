package yale.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Subclass of Task.
 * Added modification of generic Task object.
 */
public class Deadline extends Task {
    /**
     * String to represent the date and time of deadline.
     */
    protected LocalDate by;

    /**
     * Constructor method.
     * @param name Name of deadline
     * @param isMarked Boolean of whether event is marked.
     * @param by Date of Deadline.
     */
    public Deadline(String name, boolean isMarked, String by) {
        super(name, isMarked);
        this.by = LocalDate.parse(by);
    }

    /**
     * Returns a customised String format of
     * the Deadline object.
     * @return Custom String format of Deadline object.
     */
    @Override
    public String export() {
        return "D " + "| " + (isMarked? 1 : 0) + " | " + this.name + " | " + this.by;
    }
    /**
     * Returns a customised String.
     * @return Customised String format.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }
}
