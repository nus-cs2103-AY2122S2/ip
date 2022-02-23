package duke.tasks;

import duke.managers.DateTimeManager;

import java.time.LocalDateTime;

/**
 * A type of task which has a date and time of the event set to it.
 * The date and time of the event is in form of datetime and is symbolized with "[E]".
 */
public class Event extends WordListItem {
    static private final String SYMBOL = "[E]";
    private LocalDateTime datetime;

    /**
     * Constructor for event.
     * @param description description of the event
     * @param datetime datetime for the event
     */
    public Event(String description, LocalDateTime datetime) {
        super(description);
        this.datetime = datetime;
    }

    /**
     * Get the datetime of the deadline.
     * @return datetime
     */
    public LocalDateTime getDatetime() {
        return this.datetime;
    }

    /**
     * Get the symbol for the deadline.
     * @return symbol
     */
    static public String getSymbol() {
        return SYMBOL;
    }

    @Override
    public String toString() {
        String datetimestr = DateTimeManager.getDisplayString(this.datetime);
        return SYMBOL + super.toString() + " (at: " + datetimestr + ")";
    }
}
