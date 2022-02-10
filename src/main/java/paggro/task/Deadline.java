package paggro.task;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import paggro.notabledate.NotableDate;

/**
 * This class encapsulates a Task that needs to be done by a certain deadline.
 */
public class Deadline extends Task {
    /** The date on which the Task is due. */
    private NotableDate date;
    /** The time at which the Task is due, if applicable. */
    private LocalTime time;

    /**
     * Default constructor of Deadline.
     *
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
     *
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
     * Returns the NotableDate of the Deadline object.
     *
     * @return NotableDate of the deadline.
     */
    public NotableDate getDate() {
        return date;
    }

    /**
     * Parses the task into a string formatted to be saved to storage.
     *
     * @return String to be saved to storage.
     */
    @Override
    public String parseTask() {
        DateTimeFormatter dFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter tFormat = DateTimeFormatter.ofPattern("HH:mm");
        final String separator = " | ";
        final String formattedTags = super.getTags().toString()
                .replace(",", "")
                .replace("[", "")
                .replace("]", "");
        final String dateStr = this.date.getLocalDate().format(dFormat);
        String timeStr = "";
        if (time != null) {
            timeStr = time.format(tFormat);
        }
        String parseStr = "D" + separator + isDone() + separator + getDescription() + separator
                + dateStr + separator + timeStr + separator + formattedTags;

        return parseStr;
    }

    /**
     * Returns a String representation of the Deadline object.
     *
     * @return String representing the Deadline object.
     */
    @Override
    public String toString() {
        String str;
        if (isDone()) {
            str = "[D][X] ";
        } else {
            str = "[D][ ] ";
        }

        final String dateString = " (by: " + date;
        str += getDescription() + dateString;

        if (time != null) {
            str += " " + time.format(DateTimeFormatter.ofPattern("h:mma"));
        }
        str += ") | Tags:";
        int noOfTags = super.getTags().size();
        for (int i = 0; i < noOfTags; i++) {
            str += " " + super.getTags().get(i).toString();
        }
        return str;
    }
}
