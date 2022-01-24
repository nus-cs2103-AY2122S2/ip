import java.time.LocalDate;
import java.time.LocalTime;

public class Deadline extends Task {

    /** Deadline timing. */
    private LocalDate by;
    private LocalTime byTime;

    /**
     * Constructor for Deadline Class.
     * @param description The description of the deadline.
     * @param by The timing of the deadline.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
        this.byTime = null;
    }

    public Deadline(String description, LocalDate by, LocalTime byTime) {
        super(description);
        this.by = by;
        this.byTime = byTime;
    }

    /**
     * Returns the task in proper format.
     * @return String of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + " " + this.byTime + " " + ")";
    }
}
