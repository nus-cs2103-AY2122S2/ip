package duke;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a task that starts and ends at a specific date/time.
 * Includes a String representation of the date/time.
 *
 * @author Abdulelah Faisal S Al Ghrairy
 */
public class Event extends Task {

    public static final String FORMAT = "[Task] [Description] /at yyyy-mm-dd/HH:mm/HH:mm";
    private final LocalDate date;
    private final LocalTime timeBeginning;
    private final LocalTime timeEnd;

    /**
     * Constructor for Event class
     *
     * @param d     a string representing a description of the task
     * @param date  the date of the task
     * @param time1 the starting time of the event
     * @param time2 the ending time of the event
     */
    public Event(String d, LocalDate date, LocalTime time1, LocalTime time2) {
        super(d);
        this.date = date;
        this.timeBeginning = time1;
        this.timeEnd = time2;
    }

    /**
     * Returns the task properties in the format of the task to be saved onto hard disk
     *
     * @return String representing the task toString in hard-disk format
     */
    @Override
    public String toStringInFileFormat() {
        return "E|" + this.getStatusIcon() + "|" + this.description + "|"
            + this.date.format(YEAR_FORMAT) + "|"
            + this.timeBeginning.format(TIME_FORMAT) + "|"
            + this.timeEnd.format(TIME_FORMAT);
    }

    /**
     * toString implementation of task.
     *
     * @return String implementation of task
     */
    @Override
    public String toString() {
        String dateAndTime = date.format(OUTPUT_YEAR_FORMAT) + " from "
            + timeBeginning.format(OUTPUT_TIME_FORMAT) + " to "
            + timeEnd.format(OUTPUT_TIME_FORMAT);
        return "[E]" + super.toString() + " (at: " + dateAndTime + ")";
    }
}
