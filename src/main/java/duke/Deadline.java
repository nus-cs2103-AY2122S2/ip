package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that needs to be done before a specific date/time.
 * Includes a String representation of the date/time. Time here is optional.
 *
 * @author Abdulelah Faisal S Al Ghrairy
 */
public class Deadline extends Task {

    protected LocalDate date;
    protected LocalTime time;

    /**
     * Constructor for duke.Deadline class
     *
     * @param d    a string representing a description of the task
     * @param date the date of the task
     */
    public Deadline(String d, LocalDate date) {
        super(d);
        this.date = date;
    }

    /**
     * Constructor for duke.Deadline class
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
            + this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        if (time != null) {
            stringImplementationInFileFormat += "|" + this.time.format(DateTimeFormatter.ofPattern("HH:mm"));
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
        String dateAndTime = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        dateAndTime += time == null ? "" : ", " + time.format(DateTimeFormatter.ofPattern("hh:mm a"));
        return "[D]" + super.toString() + " (by: " + dateAndTime + ")";
    }
}
