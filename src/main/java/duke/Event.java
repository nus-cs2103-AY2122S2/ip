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
    protected final LocalDate date;
    protected final LocalTime timeBeginning;
    protected final LocalTime timeEnd;

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
     * Compares this instance with a Task
     * @param t1 a Task object
     * @return integer representing which deadline takes precedence
     */
    @Override
    public int compareTo(Task t1) {
        if (t1 instanceof ToDo) {
            ToDo todo = (ToDo) t1;
            return this.compareTo(todo);
        } else if (t1 instanceof Deadline) {
            Deadline deadline = (Deadline) t1;
            return this.compareTo(deadline);
        } else if (t1 instanceof Event) {
            Event event = (Event) t1;
            return this.compareTo(event);
        }
        return 0;
    }

    /**
     * Compares this instance with a ToDo
     * @param t1 a ToDo object
     * @return 1
     */
    public int compareTo(ToDo t1) {
        return 1; //ToDo takes precedence
    }

    /**
     * Compares this instance with a Deadline
     * @param d1 a Deadline object
     * @return integer representing which takes precedence
     */
    public int compareTo(Deadline d1) {
        int compare = this.date.compareTo(d1.date);
        if (compare == 0) {
            if (d1.time == null) {
                return 1;
            } else {
                return this.timeBeginning.compareTo(d1.time);
            }
        }
        return compare;
    }

    /**
     * Compares this instance with another Event
     * @param e1 another event object
     * @return integer representing which  takes precedence
     */
    public int compareTo(Event e1) {
        int compare = this.date.compareTo(e1.date);
        if (compare == 0) {
            compare = this.timeBeginning.compareTo(e1.timeBeginning);
            if (compare == 0) {
                return this.timeEnd.compareTo(e1.timeEnd);
            }
        }
        return compare;
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
