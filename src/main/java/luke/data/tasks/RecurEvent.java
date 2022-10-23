package luke.data.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Map;

import luke.parser.DateTimeParser;

/**
 * Implements a recurring event task.
 */
public class RecurEvent extends RecurringTask {

    protected LocalDateTime date;

    /**
     * Constructs a recurring event task with the specified description.
     *
     * @param description The specified description for the task.
     * @param at          The datetime to indicate at which time the event starts.
     * @param every       The recurring duration.
     * @param next        The next date to recur from.
     * @throws DateTimeParseException If the datetime is not one of the format accepted by {@code DateTimeParser}
     */
    public RecurEvent(String description, String at, String every, String next) throws DateTimeParseException {
        super(description, every, next);
        this.date = DateTimeParser.toLocalDateTime(at.stripTrailing());
        updateDate();
    }

    /**
     * Constructs a recurring event task with the argument map.
     *
     * @param args The map containing both the arguments required by Event class.
     * @throws DateTimeParseException If the datetime is not one of the format accepted by {@code DateTimeParser}
     */
    public RecurEvent(Map<String, String> args) throws DateTimeParseException {
        this(args.get("description"), args.get("at"), args.get("every"), args.get("next"));
    }

    private void updateDate() {
        LocalDateTime oldDate = date;
        while (date.isBefore(LocalDateTime.now())) {
            date = recurFunction.apply(date);
        }
        isNew = !oldDate.isEqual(date);
    }

    @Override
    public String toString() {
        return String.format(super.toString(), "[E]", "\n\t(at: " + DateTimeParser.toString(this.date) + ")");
    }

    @Override
    public String getCommandString() {
        String baseFormat = super.getCommandString();
        String taskFormat = String.format("event %s /at %s", description, DateTimeParser.toCommandString(this.date));
        return String.format(baseFormat, taskFormat);
    }
}
