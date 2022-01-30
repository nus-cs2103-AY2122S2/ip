package luke.data.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Map;

import luke.parser.DateTimeParser;

/**
 * Implements an event task.
 */
public class Event extends Task {

    protected LocalDateTime date;

    /**
     * Constructs an event task with the specified description.
     * @param description The specified description for the task.
     * @param at The datetime to indicate at which time the event starts.
     * @throws DateTimeParseException If the datetime is not one of the format accepted by {@code DateTimeParser}
     */
    public Event(String description, String at) throws DateTimeParseException {
        super(description);
        this.date = DateTimeParser.toLocalDateTime(at.stripTrailing());
    }

    /**
     * Constructs an event task with the argument map.
     * @param args The map containing both the arguments required by Event class.
     * @throws DateTimeParseException If the datetime is not one of the format accepted by {@code DateTimeParser}
     */
    public Event(Map<String, String> args) throws DateTimeParseException {
        this(args.get("description"), args.get("at"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + DateTimeParser.toString(this.date) + ")";
    }

    @Override
    public String getCommandString() {
        return String.format("event %s /at %s", description, DateTimeParser.toCommandString(this.date));
    }
}
