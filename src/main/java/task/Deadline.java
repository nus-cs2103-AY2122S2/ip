
package task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * Represents a type of Task - Deadline.
 * Allows for Date or Time to be specified which distinguishes
 * itself from an Event or Todo.
 */
public class Deadline extends Task {

    public static final String DATETIME_FORMAT = "yyyy-MM-dd HHmm";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATETIME_OUTPUT_FORMAT = "MMM dd yyyy HHmm";
    public static final String TIME_OUTPUT_FORMAT = "MMM dd yyyy";

    protected String by;
    protected LocalDateTime datetime = null;
    protected LocalDate date = null;

    /**
     * Class constructor.
     * Automatically converts the String-type date/time.
     *
     * @param description Description of deadline
     * @param by Date/Time of the deadline
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        convertToDate(by);
    }

    /**
     * Converts a String to DateTime or Date.
     * If fail to parse String into Date or DateTime type,
     * DukeException is thrown.
     *
     * @param by Date/Time of the deadline
     * @throws DateTimeParseException If Date/Time is invalid format
     */
    private void convertToDate(String by) throws DateTimeParseException {
        /* Date format is invalid */
        String[] split = by.split("\\s+");
        if (split.length > 1) {
            this.datetime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern(DATETIME_FORMAT));
        } else {
            this.date = LocalDate.parse(by, DateTimeFormatter.ofPattern(DATE_FORMAT));
        }
    }

    /**
     * Formats the DateTime/Date object into MMM dd yyyy (HHmm) format.
     *
     * @return New Date/Date format
     */
    public String formatDeadline() {
        if (this.datetime != null) {
            return datetime.format(DateTimeFormatter.ofPattern(DATETIME_OUTPUT_FORMAT));
        } else {
            return date.format(DateTimeFormatter.ofPattern(TIME_OUTPUT_FORMAT));
        }
    }

    /**
     * Returns deadline details.
     *
     * @return Output string to indicate deadline's details.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDeadline() + ")";
    }
}
