import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Map;

public class Deadline extends Task {

    protected LocalDateTime date;

    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);
        this.date = DateTimeParser.toLocalDateTime(by.stripTrailing());
    }

    public Deadline(Map<String, String> args) throws DateTimeParseException {
        this(args.get("description"), args.get("by"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTimeParser.toString(this.date) + ")";
    }

    @Override
    public String getCommandString() {
        return String.format("deadline %s /by %s", description, DateTimeParser.toCommandString(this.date));
    }
}