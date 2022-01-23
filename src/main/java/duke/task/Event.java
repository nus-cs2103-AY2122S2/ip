package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the event a user would create. A <code>Event</code> object is a subclass of the Task class
 * and corresponds to an event inputted by a user.
 */
public class Event extends Task {
    protected LocalDate date;
    protected LocalTime startTime;
    protected LocalTime endTime;

    /**
     * Constructor for the Event class.
     * @param description information about the event.
     * @param date date details about the deadline.
     * @param startTime starting time of the deadline.
     * @param endTime ending time of the deadline.
     */
    public Event(String description, LocalDate date, LocalTime startTime, LocalTime endTime) {
        super(description);
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public LocalTime getStartTime() {
        return this.startTime;
    }

    public LocalTime getEndTime() {
        return this.endTime;
    }

    /**
     * Returns the string representation of an event.
     * @return a string representation of the event, consisting of its description, formatted date,
     * starting time and ending time.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " " + startTime.format(DateTimeFormatter.ofPattern("h:mm a")) + " to"
                + " " + endTime.format(DateTimeFormatter.ofPattern("h:mm a")) + ")";
    }
}
