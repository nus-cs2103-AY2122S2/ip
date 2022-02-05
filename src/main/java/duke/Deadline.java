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

    protected final LocalDate date;
    protected final LocalTime time;

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
     * Compares this instance with another Deadline
     * @param d1 another Deadline object
     * @return integer representing which deadline takes precedence
     */
    public int compareTo(Deadline d1) {
        int compare = this.date.compareTo(d1.date);
        if (compare == 0) {
            if (d1.time == null) {
                return 1;
            } else if (this.time == null) {
                return -1;
            } else {
                return d1.time.compareTo(this.time);
            }
        }
        return compare;
    }

    /**
     * Compares this instance with an Event
     * @param e1 event object
     * @return integer representing which task takes precedence
     */
    public int compareTo(Event e1) {
        int compare = this.date.compareTo(e1.date);
        if (compare == 0) {
            if (this.time == null) {
                return -1;
            } else {
                return this.time.compareTo(e1.timeBeginning);
            }
        }
        return compare;
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
