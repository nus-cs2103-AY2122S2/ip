package luke.data.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Map;

import luke.parser.DateTimeParser;

/**
 * Implements a Deadline task.
 */
public class Deadline extends Task {

    protected LocalDateTime date;

    /**
     * Constructs a deadline task with the specified description.
     * @param description The specified description for the task.
     * @param by The datetime to indicate by when the task should be completed.
     * @throws DateTimeParseException If the datetime is not one of the format accepted by {@code DateTimeParser}
     */
    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);
        this.date = DateTimeParser.toLocalDateTime(by.stripTrailing());
    }

    /**
     * Constructs a deadline task with the argument map.
     * @param args The map containing both the arguments required by Deadline class.
     * @throws DateTimeParseException If the datetime is not one of the format accepted by {@code DateTimeParser}
     */
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
