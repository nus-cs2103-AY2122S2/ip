package duke;
import java.time.LocalDate;

/**
 * Class {@code Deadline}
 * Extends {@code Task}
 */
public class Deadline extends Task {

    protected String by;
    protected LocalDate date;

    /**
     * Default Constructor for {@code Deadline}
     *
     * @param description Description of the Deadline Task
     * @param by String Representation of Due Date.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Overloaded Constructor for {@code Deadline}
     *
     * @param description Description of the Deadline Task
     * @param date LocalDate representation of Due Date.
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
        this.by = date.getDayOfMonth() + " " + date.getMonth() + " " + date.getYear();
    }

    /**
     * Returns Status Icon of current task as a String representation
     *
     * @return String representation of current task status icon
     */
    public String getStatusIcon() {
        return "[D]" + super.getStatusIcon();
    }

    /**
     * Returns String representation of {@code Deadline} Task.
     *
     * @return String representation of task of format {@code StatusIcon + Description}
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}