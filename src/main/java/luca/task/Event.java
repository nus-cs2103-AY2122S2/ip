package luca.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Tasks that has as specific start time and end times.
 */
public class Event extends Task {

    /** String representation of the event start dates/times. */
    protected LocalDateTime start;

    /** String representation of the event end dates/times. */
    protected LocalDateTime end;

    /**
     * Constructor to create a Event task.
     *
     * @param description text description of the Event.
     * @param start start dates/times.
     * @param end end dates/times.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description, TaskType.EVENT);
        this.start = start;
        this.end = end;
    }

    /**
     * Outputs the start and end times of event in a
     * formatted manner.
     *
     * @return the start and end time of Event.
     */
    public String getAt() {
        return start.format(DateTimeFormatter.ofPattern("h:mm a")) + " - "
                + end.format(DateTimeFormatter.ofPattern("h:mm a MMM dd yyyy"));
    }

    /**
     * Outputs the string of start date/time using LocalDateTime such that
     * can be parsed by Java.
     *
     * @return deadline as string convenient to be parsed.
     */
    public String startToString() {
        return start.toString();
    }

    /**
     * Outputs the string of end date/time using LocalDateTime such that
     * can be parsed by Java.
     *
     * @return deadline as string convenient to be parsed.
     */
    public String endToString() {
        return end.toString();
    }

    /**
     * Outputs the storage string/format for the task.
     *
     * @return string formatted for storage.
     */
    public String getStorageString() {
        return "E | " + (getIsDone() ? "1" : "0") + " | " + getDescription()
                + " | " + startToString() + " | " + endToString() + "\n";
    }

    /**
     * Outputs the string to represent Event with
     * description and at dates/times.
     *
     * @return formatted string representing the Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getAt() + ")";
    }
}
