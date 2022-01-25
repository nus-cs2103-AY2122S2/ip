package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a user's event.
 */
public class Event extends Task {
    private static final DateTimeFormatter dateOut = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final DateTimeFormatter timeOut = DateTimeFormatter.ofPattern("hh:mm a");
    protected LocalDate d;
    protected LocalTime t;
    private final boolean hasDate;
    private final boolean hasTime;

    /**
     * Creates a new event with a date.
     *
     * @param description the description of the event
     * @param d the date of the event
     */
    public Event(String description, LocalDate d) {
        super(description);
        this.d = d;
        hasDate = true;
        hasTime = false;
    }

    /**
     * Creates a new event with a time.
     *
     * @param description the description of the event
     * @param t the time of the event
     */
    public Event(String description, LocalTime t) {
        super(description);
        this.t = t;
        hasDate = false;
        hasTime = true;
    }

    /**
     * Creates a new event with a date and time.
     *
     * @param description the description of the event
     * @param d the date of the event
     * @param t the time of the event
     */
    public Event(String description, LocalDate d, LocalTime t) {
        super(description);
        this.d = d;
        this.t = t;
        hasDate = true;
        hasTime = true;
    }

    /**
     * Retrieves the date of the event.
     *
     * @return the date of the event formatted in "MMM dd YYYY"
     */
    private String getDate() {
        return d.format(dateOut);
    }

    /**
     * Retrieves the time of the event.
     *
     * @return the time of the event formatted in "hh:mm am/pm"
     */
    private String getTime() {
        return t.format(timeOut);
    }

    /**
     * Retrieves the date and time of the event.
     *
     * @return the date and time of the event formatted in "MMM dd YYYY hh:mm am/pm"
     */
    private String getDateTime() {
        return d.format(dateOut) + " " + t.format(timeOut);
    }

    @Override
    public boolean isHasDate() {
        return hasDate;
    }

    @Override
    public boolean isHasTime() {
        return hasTime;
    }

    @Override
    public String getAppendData() {
        return "E | " + (super.isDone ? "1" : "0") + " | " + description + " | " +
                (hasDate ? d.toString() : "0") + " | " +
                (hasTime ? t.toString() : "0");
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +
                (hasDate && hasTime
                        ? getDateTime()
                        : hasDate
                        ? getDate()
                        : getTime()) + ")";
    }
}
