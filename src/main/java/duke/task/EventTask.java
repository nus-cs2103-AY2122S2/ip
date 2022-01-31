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
     * Constructor for event with date only.
     *
     * @param title Title of event
     * @param eventDate Date of event
     */
    public EventTask(String title, String eventDate) {
        super(title);
        this.type = TaskType.EVENT;
        this.eventDate = LocalDate.parse(eventDate);
        this.eventTime = null;
    }

    /**
     * Constructor for event with date and time.
     *
     * @param title Title of event
     * @param eventDate Date of event
     * @param eventTime Time of event
     */
    public EventTask(String title, String eventDate, String eventTime) {
        super(title);
        this.type = TaskType.EVENT;
        this.eventDate = LocalDate.parse(eventDate);
        this.eventTime = LocalTime.parse(eventTime);
    }

    /**
     * Constructor for event with specified done state and date.
     *
     * @param title Title of event
     * @param isDone Done state
     * @param eventDate Date of event
     */
    public EventTask(String title, Boolean isDone, String eventDate) {
        super(title, isDone);
        this.type = TaskType.EVENT;
        this.eventDate = LocalDate.parse(eventDate);
        this.eventTime = null;
    }

    /**
     * Constructor for event with speficied done state, date and time.
     *
     * @param title Title of event
     * @param isDone Done state
     * @param eventDate Date of event
     * @param eventTime Time of event
     */
    public EventTask(String title, Boolean isDone, String eventDate, String eventTime) {
        super(title, isDone);
        this.type = TaskType.EVENT;
        this.eventDate = LocalDate.parse(eventDate);
        this.eventTime = LocalTime.parse(eventTime);
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
