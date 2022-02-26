package sparrow.logic.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import sparrow.model.Priority;
import sparrow.model.Status;

/**
 * Represents a deadline.
 */
public class Deadline extends Task {
    private final LocalDateTime by;
    /**
     * Constructs a deadline.
     * @param d The deadline description.
     * @param s The deadline status.
     * @param p The deadline priority.
     * @param b The deadline datetime.
     */
    public Deadline(String d, Status s, Priority p, String b) {
        super(d, s, p);
        by = parseDateTime(b);
    }

    /**
     * Constructs a deadline.
     * @param d The deadline description.
     * @param b The deadline datetime.
     */
    public Deadline(String d, String b) {
        super(d);
        by = parseDateTime(b);
    }

    /**
     * Returns the deadline as a string which can be saved and loaded as a deadline again.
     *
     * @return Deadline as a string which can be saved and loaded as a deadline again.
     */
    @Override
    public String save() {
        String dateTime = by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        return "D | " + super.save() + " | " + dateTime + " | " + savePriority() + "\n";
    }

    /**
     * Returns the deadline as a readable string.
     *
     * @return Deadline as a readable string.
     */
    @Override
    public String toString() {
        String dateTime = by.format(DateTimeFormatter.ofPattern("MMM d HHmm"));
        return "[D] " + super.toString() + " (by " + dateTime + "H)\n";
    }
}
