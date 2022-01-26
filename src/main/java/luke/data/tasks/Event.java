package luke.data.tasks;

import luke.parser.DateTimeParser;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Map;

public class Event extends Task {

    protected LocalDateTime date;

    public Event(String description, String at) throws DateTimeParseException {
        super(description);
        this.date = DateTimeParser.toLocalDateTime(at.stripTrailing());
    }

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
