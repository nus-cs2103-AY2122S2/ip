package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Tasks that are classified as events.
 */
public class EventTask extends Task {
    protected LocalDate eventDate;
    protected LocalTime eventTime;

    /**
     * Constructor for event with date and/or time
     *
     * @param title Title of event
     * @param eventDateTime Date of event
     */
    public EventTask(String title, String... eventDateTime) {
        super(title);
        this.type = TaskType.EVENT;
        assert(eventDateTime.length > 0 && eventDateTime.length <= 2);
        if (eventDateTime.length >= 1) {
            this.eventDate = LocalDate.parse(eventDateTime[0]);
        }
        if (eventDateTime.length == 2) {
            this.eventTime = LocalTime.parse(eventDateTime[1]);
        }
    }

    /**
     * Constructor for event with specified done state, date and/or time.
     *
     * @param title Title of event
     * @param isDone Done state
     * @param eventDateTime Date of event
     */
    public EventTask(String title, Boolean isDone, String... eventDateTime) {
        super(title, isDone);
        this.type = TaskType.EVENT;
        assert(eventDateTime.length > 0 && eventDateTime.length <= 2);
        if (eventDateTime.length >= 1) {
            this.eventDate = LocalDate.parse(eventDateTime[0]);
        }
        if (eventDateTime.length == 2) {
            this.eventTime = LocalTime.parse(eventDateTime[1]);
        }
    }


    /**
     * Concatenate event date and time printing string.
     *
     * @return String for printing the event date and time
     */
    public String getEventTime() {
        return "(at: " + eventDate + (eventTime != null ? " " + eventTime.toString() : "") + ")";
    }

    /**
     * Concatenate event into format to save to file.
     *
     * @return String for file saving
     */
    public String toOutputLine() {
        return this.getType() + " | " + (isDone ? "1 " : "0 ") + "| " + this.title
                + " | " + eventDate.toString() + (eventTime != null ? " | " + eventTime.toString() : "");
    }

    /**
     * Combines the title with the event time.
     * Used when printing the event with the List command.
     *
     * @return String with the title and event date and time
     */
    @Override
    public String toString() {
        return this.title + " " + getEventTime();
    }
}
