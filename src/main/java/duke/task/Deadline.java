package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task which has a deadline.
 */
public class Deadline extends Task {

    /** The date of the deadline */
    private final LocalDate deadlineDate;
    /** The time of day of the deadline */
    private final LocalTime deadlineTime;

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
        this.deadlineDate = by;
        this.deadlineTime = byTime;
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
    public String convertToFileFormat() {
        return " D " + super.convertToFileFormat() + " | " + this.deadlineDate + " | " + this.deadlineTime;
    }

    /**
     * Adds extra custom formatting for user view specific to deadlines.
     *
     * @return A custom String display of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.deadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " "
                + this.deadlineTime.format(DateTimeFormatter.ofPattern("hh:mm a")) + ")";
    }
}
