import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Map;

public class Event extends Task {

    protected LocalDateTime date;
    public Event(String description, String at) throws DateTimeParseException {
        super(description);
        this.date = DateTimeParser.toLocalDateTime(at);
    }

    public Event(Map<String,String> args) throws DateTimeParseException {
        this(args.get("description"), args.get("at"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + DateTimeParser.toString(date) + ")";
    }
}
