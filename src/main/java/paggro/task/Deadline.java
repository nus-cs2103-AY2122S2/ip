package paggro.task;

import java.time.format.DateTimeFormatter;
import java.time.LocalTime;

import paggro.notableDate.NotableDate;

/**
 * This class encapsulates a Task that needs to be done by a certain deadline.
 */
public class Deadline extends Task {
    /** The date on which the Task is due. */
    public NotableDate date;
    /** The time at which the Task is due, if applicable. */
    LocalTime time;

    /**
     * Default constructor of Deadline.
     * @param des String description of the Deadline object.
     * @param date The date on which the Task is due.
     * @param isDone A boolean indicating if the task is done.
     */
    public Deadline(String des, NotableDate date, boolean isDone) {
        super(des, isDone);
        this.date = date;
    }

    /**
     * Constructor of Deadline object specifying if the task is complete.
     * @param des String description of the Deadline object.
     * @param date The date on which the Task is due.
     * @param isDone A boolean indicating if the task is done.
     */
    public Deadline(String des, NotableDate date, LocalTime time, boolean isDone) {
        super(des, isDone);
        this.date = date;
        this.time = time;
    }

    /**
     * Parses the task into a string formatted to be saved to storage.
     * @return String to be saved to storage.
     */
    @Override
    public String parseTask() {
        DateTimeFormatter dFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter tFormat = DateTimeFormatter.ofPattern("HH:mm");
        if (time == null) {
            return "D | " + Boolean.toString(isDone) + " | " + description + " | " + date.localDate.format(dFormat);
        } else {
            return "D | " + Boolean.toString(isDone) + " | " + description + " | " + date.localDate.format(dFormat)
                    + " | " + time.format(tFormat);
        }
    }

    /**
     * Returns a String representation of the Deadline object.
     * @return String representing the Deadline object.
     */
    @Override
    public String toString() {
        String str;
        if (isDone) {
            str = "[D][X] " + description + " (by: " + date;
        } else {
            str = "[D][ ] " + description + " (by: " + date;
        }

        if (time != null) {
            str += " " + time.format(DateTimeFormatter.ofPattern("h:mma"));
        }
        str += ")";
        return str;
    }
}
