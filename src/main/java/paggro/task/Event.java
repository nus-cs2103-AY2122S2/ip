package paggro.task;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import paggro.notabledate.NotableDate;

/**
 * This class encapsulates an event occurring on a given date.
 */
public class Event extends Task {
    /** The date on which the Task is due. */
    private NotableDate date;
    /** The time at which the Task is due, if applicable. */
    private LocalTime time;

    /**
     * Default constructor of Event.
     *
     * @param des String description of the Event object.
     * @param date The date on which the Task is due.
     * @param isDone A boolean indicating if the task is done.
     */
    public Event(String des, NotableDate date, boolean isDone) {
        super(des, isDone);
        this.date = date;
    }

    /**
     * Constructor of the Event object specifying if the task is complete.
     *
     * @param des String description of the Event object.
     * @param date The date on which the event is occurring.
     * @param isDone A boolean indicating if the task is done.
     */
    public Event(String des, NotableDate date, LocalTime time, boolean isDone) {
        super(des, isDone);
        this.date = date;
        this.time = time;
    }

    /**
     * Returns the NotableDate of the Event object.
     *
     * @return NotableDate of the event.
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

        String parseStr = "E" + separator + isDone() + separator + getDescription() + separator
                + date.getLocalDate().format(dFormat);

        if (time != null) {
            parseStr += time.format(tFormat);
        }

        return parseStr;
    }

    /**
     * Returns a String representation of the Event object.
     *
     * @return String representing the Event object.
     */
    @Override
    public String toString() {
        String str;
        if (isDone()) {
            str = "[E][X] ";
        } else {
            str = "[E][ ] ";
        }

        final String dateString = " (at: " + date;
        str += getDescription() + dateString;

        if (time != null) {
            str += " " + time.format(DateTimeFormatter.ofPattern("h:mma"));
        }

        str += ")";
        return str;
    }
}

