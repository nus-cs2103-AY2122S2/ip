package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task.
 */
public class Event extends Task {
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    /**
     * An Event constructor to initialise a <code>Event</code> object. An <code>Event</code>
     * corresponds to a task represented by a String, LocalDate, LocalTime, LocalTime.
     * E.g., <code>do project, 12-12-2022, 1900, 2200</code>.
     *
     * @param description the description of the event task to be done.
     * @param date the date of the event of the task.
     * @param startTime the start time of the event of the task.
     * @param endTime the end time of the event of the task.
     */
    public Event(String description, LocalDate date, LocalTime startTime, LocalTime endTime) {
        super(description);
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * An Event constructor to initialise a <code>Event</code> object. An <code>Event</code>
     * corresponds to a task represented by a String, LocalDate, LocalTime, LocalTime, and Tag.
     * E.g., <code>do project, 12-12-2022, 1900, 2200</code>.
     *
     * @param description the description of the event task to be done.
     * @param date the date of the event of the task.
     * @param startTime the start time of the event of the task.
     * @param endTime the end time of the event of the task.
     * @param tag the tag of the task to be added.
     */
    public Event(String description, LocalDate date, LocalTime startTime, LocalTime endTime, Tag tag) {
        super(description, tag);
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * Returns the string representation of the <code>Event</code> task.
     *
     * @return the string representation of the <code>Event</code> task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                + " " + startTime.format(DateTimeFormatter.ofPattern("hh:mma")) + " - "
                + endTime.format(DateTimeFormatter.ofPattern("hh:mma")) + ")";
    }
}
