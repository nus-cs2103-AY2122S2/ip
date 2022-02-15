package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task made by the user. A <code>Event</code> object stores
 * the description, date, start time and end time of the event. The task can
 * be mark as done after the user complete the specific task.
 */
public class Event extends Task {
    protected LocalDate eventDate;
    protected LocalTime startTime;
    protected LocalTime endTime;

    /**
     * Creates an instance of an Event object.
     *
     * @param description the description of the event task.
     * @param eventDate date of the event.
     * @param startTime start time of the event.
     * @param endTime end time of the event.
     */
    public Event(String description, LocalDate eventDate, LocalTime startTime, LocalTime endTime) {
        super(description);
        this.eventDate = eventDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Creates an instance of an Event object.
     *
     * @param description the description of the event task.
     * @param isMark whether the event task is marked.
     * @param eventDate date of the event.
     * @param startTime start time of the event.
     * @param endTime end time of the event.
     */
    public Event(String description, boolean isMark, LocalDate eventDate, LocalTime startTime, LocalTime endTime) {
        super(description, isMark);
        this.eventDate = eventDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns the visual description of the event task.
     *
     * @return description of the event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d MMM yyyy");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("h:mma");

        return String.format("[E]%s (at: %s %s - %s)", super.toString(), eventDate.format(dateFormat),
                startTime.format(timeFormat), endTime.format(timeFormat));
    }

    /**
     * Returns the data of the event task.
     *
     * @return data of the event task.
     */
    @Override
    public String toData() {
        return "E|" + super.toData() + "|" + eventDate + "|" + startTime + "|" + endTime;
    }
}
