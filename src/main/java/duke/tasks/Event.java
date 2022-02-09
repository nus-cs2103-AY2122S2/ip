package duke.tasks;

import duke.managers.DateTimeManager;

import java.time.LocalDateTime;

public class Event extends WordListItem {
    static private final String SYMBOL = "[E]";
    private LocalDateTime datetime;

    public Event(String description, LocalDateTime datetime) {
        super(description);
        this.datetime = datetime;
    }

    public LocalDateTime getDatetime() {
        return this.datetime;
    }

    static public String getSymbol() {
        return SYMBOL;
    }

    @Override
    public String toString() {
        String datetimestr = DateTimeManager.getDisplayString(this.datetime);
        return SYMBOL + super.toString() + " (at: " + datetimestr + ")";
    }
}
