package duke.tasks;

import duke.managers.DateTimeManager;

import java.time.LocalDateTime;

/**
 * A type of task which has a deadline set to it.
 * The deadline is in form of datetime and is symbolized with "[D]".
 */
public class Deadline extends WordListItem {
    static private final String SYMBOL = "[D]";
    private LocalDateTime datetime;

    /**
     * Constructor for deadline.
     * @param description description of the deadline
     * @param datetime datetime for the deadline
     */
    public Deadline(String description, LocalDateTime datetime) {
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
        return SYMBOL + super.toString() + " (by: " + datetimestr + ")";
    }
}
