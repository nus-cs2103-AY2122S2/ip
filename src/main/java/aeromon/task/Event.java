package aeromon.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event class handles the Event Task type.
 */
public class Event extends Task {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private final LocalDate at;

    private static final String EVENT_STRING_FORMAT = "[E]%1$s (at: %2$s)";
    private static final String EVENT_OUTPUT_FORMAT = "E / %1$s / %2$s";

    /**
     * Constructs the Event object.
     * @param description the task name.
     * @param at the duration in which the event takes place.
     */
    public Event(String description, boolean isDone, LocalDate at) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String toString() {
        return String.format(EVENT_STRING_FORMAT, super.toString(), at.format(DATE_TIME_FORMATTER));
    }

    @Override
    public String toOutputFormat() {
        return String.format(EVENT_OUTPUT_FORMAT, super.toOutputFormat(), at.format(DATE_TIME_FORMATTER));
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Event)) {
            return false;
        } else {
            Event obj = (Event) object;
            return this.description.equals(obj.description) && this.at.equals(obj.at);
        }
    }
}
