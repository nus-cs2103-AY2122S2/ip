package duke;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a task that needs to be done before a specific date/time.
 * Includes a String representation of the date/time. Time here is optional.
 *
 * @author Abdulelah Faisal S Al Ghrairy
 */
public class Deadline extends Task {

    public static final String FORMAT = "[Task] [Description] /by yyyy-mm-dd/HH:mm (leave \"/HH:mm\"\n"
        + "            + \" empty if no time in current task)";

    private final LocalDate date;
    private final LocalTime time;

    /**
     * Constructor for Deadline class
     *
     * @param d    a string representing a description of the task
     * @param date the date of the task
     */
    public Deadline(String d, LocalDate date) {
        super(d);
        this.date = date;
        this.time = null;
    }

    /**
     * Constructor for Deadline class that include a time reference
     *
     * @param d    a string representing a description of the task
     * @param date the date of the task
     * @param time the time of the task
     */
    public Deadline(String d, LocalDate date, LocalTime time) {
        super(d);
        this.date = date;
        this.time = time;
    }

    /**
     * Returns the task properties in the format of the task to be saved onto hard disk
     *
     * @return String representing the task toString in hard-disk format
     */
    public String toStringInFileFormat() {
        String stringImplementationInFileFormat = "D|" + this.getStatusIcon() + "|" + this.description + "|"
            + this.date.format(YEAR_FORMAT);

        if (time != null) {
            stringImplementationInFileFormat += "|" + this.time.format(TIME_FORMAT);
        }

        return stringImplementationInFileFormat;
    }

    /**
     * Returns the toString implementation of task.
     *
     * @return String implementation of task
     */
    @Override
    public String toString() {
        String dateAndTime = date.format(OUTPUT_YEAR_FORMAT);
        dateAndTime += time == null ? "" : ", " + time.format(OUTPUT_TIME_FORMAT);
        return "[D]" + super.toString() + " (by: " + dateAndTime + ")";
    }
}
