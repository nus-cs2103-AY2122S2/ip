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
     * Constructor for Deadline class
     * @param d a string representing a description of the task
     * @param date the date of the task
     */
    public Deadline(String d, LocalDate date) {
        super(d);
        this.date = date;
    }

    /**
     * Constructor for Deadline class
     * @param d a string representing a description of the task
     * @param date the date of the task
     * @param time the time of the task
     */
    public Deadline(String d, LocalDate date, LocalTime time) {
        super(d);
        this.date = date;
        this.time = time;
    }

    @Override
    /**
     * toString implementation of task that includes Date/Time
     */
    public String toString() {
        String dateAndTime = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        dateAndTime += time == null ? "" : ", " + time.format(DateTimeFormatter.ofPattern("hh:mm a"));
        return "[D]" + super.toString() + " (by: " + dateAndTime + ")";
    }
}
