import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that starts and ends at a specific date/time.
 * Includes a String representation of the date/time.
 *
 * @author Abdulelah Faisal S Al Ghrairy
 */
public class Event extends Task {

    protected LocalDate date;
    protected LocalTime timeBeginning, timeEnd;

    /**
     * Constructor for Event class
     * @param d a string representing a description of the task
     * @param date the date of the task
     * @param time1 the starting time of the event
     * @param time2 the ending time of the event
     */
    public Event(String d, LocalDate date, LocalTime time1, LocalTime time2) {
        super(d);
        this.date = date;
        this.timeBeginning = time1;
        this.timeEnd = time2;
    }

    @Override
    /**
     * toString implementation of task that includes Date/Time
     */
    public String toString() {
        String dateAndTime = date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " from " +
                timeBeginning.format(DateTimeFormatter.ofPattern("hh:mm a")) + " to " +
                timeEnd.format(DateTimeFormatter.ofPattern("hh:mm a"));
        return "[E]" + super.toString() + " (at: " + dateAndTime + ")";
    }
}
