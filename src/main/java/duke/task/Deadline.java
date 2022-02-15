package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 *
 * @author Peter
 */
public class Deadline extends Task {
    /**
     * Time associated with the deadline task.
     */
    private final LocalDateTime byTime;

    /**
     * Constructor for a deadline task.
     *
     * @param description Description associated with the deadline task.
     * @param isMarked    Boolean flag of whether the deadline task is done.
     * @param byTime      Time associated with the deadline task.
     */
    public Deadline(String description, boolean isMarked, LocalDateTime byTime) {
        super(description, isMarked);
        this.byTime = byTime;
    }

    /**
     * Converts the deadline task to a form legible by the storage.
     *
     * @return Data representation of the deadline task.
     */
    @Override
    public String toData() {
        return "D | " + this.isMarked + " | " + this.description + " | " + this.byTime;
    }

    /**
     * Creates a copy of the deadline task.
     *
     * @return Copy of the deadline task.
     */
    @Override
    public Deadline copy() {
        return new Deadline(this.description, this.isMarked, this.byTime);
    }

    /**
     * Reformats and returns string representation of deadline task.
     *
     * @return Reformatted string representation of deadline task.
     */
    @Override
    public String toString() {
        String byTimeFormatted = this.byTime.format(DateTimeFormatter.ofPattern(
                "HH:mm, MMM dd yyyy"));
        return "[D]" + super.toString() + " (by: " + byTimeFormatted + ")";
    }
}
