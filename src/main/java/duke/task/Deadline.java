package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task which has a deadline.
 */
public class Deadline extends Task {

    /** The date of the deadline */
    private LocalDate by;

    /** The time of day of the deadline */
    private LocalTime byTime;

    /**
     * Constructor for Deadline Class.
     *
     * @param description The description of the deadline.
     * @param by The date of the deadline as a LocalDate object.
     * @param isDone The completion status of the deadline.
     */
    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description);
        this.by = by;
        this.byTime = null;
            if (isDone) {
                this.markAsDone();
            }
    }

    /**
     * Constructor for Deadline Class.
     *
     * @param description The description of the deadline.
     * @param by The date of the deadline as a LocalDate object.
     * @param byTime The time of day of the deadline as a LocalTime object.
     * @param isDone The completion status of the deadline.
     */
    public Deadline(String description, LocalDate by, LocalTime byTime, boolean isDone) {
        super(description);
        this.by = by;
        this.byTime = byTime;
        if (isDone) {
            this.markAsDone();
        }
    }

    /**
     * Returns a standardized format for storing the deadline into the data file.
     *
     * @return String of deadline in data file storage format.
     */
    @Override
    public String writeToFile() {
        return " D " + super.writeToFile() + " | " + this.by + " | " + this.byTime;
    }

    /**
     * Adds extra custom formatting for user view specific to deadlines.
     *
     * @return A custom String display of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " " + this.byTime.format(DateTimeFormatter.ofPattern("hh:mm a")) + " " + ")";
    }
}
