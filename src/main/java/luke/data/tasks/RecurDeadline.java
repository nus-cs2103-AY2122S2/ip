package luke.data.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Map;

import luke.parser.DateTimeParser;

/**
 * Implements a recurring deadline task.
 */
public class RecurDeadline extends RecurringTask {

    protected LocalDateTime date;
    /**
     * Constructs a recurring deadline task with the specified description.
     *
     * @param description The specified description for the task.
     * @param by          The datetime to indicate by when the task should be completed.
     * @param every       The recurring duration.
     * @param next        The next date to recur from.
     * @throws DateTimeParseException If the datetime is not one of the format accepted by {@code DateTimeParser}
     */
    public RecurDeadline(String description, String by, String every, String next) throws DateTimeParseException {
        super(description, every, next);
        this.date = DateTimeParser.toLocalDateTime(by.stripTrailing());
        updateDate();
    }

    /**
     * Constructs a recurring deadline task with the argument map.
     *
     * @param args The map containing both the arguments required by Deadline class.
     * @throws DateTimeParseException If the datetime is not one of the format accepted by {@code DateTimeParser}
     */
    public RecurDeadline(Map<String, String> args) throws DateTimeParseException {
        this(args.get("description"), args.get("by"), args.get("every"), args.get("next"));
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
        return String.format(super.toString(), "[D]", "\n\t(by: " + DateTimeParser.toString(this.date) + ")");
    }

    @Override
    public String getCommandString() {
        String baseFormat = super.getCommandString();
        String taskFormat = String.format("deadline %s /by %s", description, DateTimeParser.toCommandString(this.date));
        return String.format(baseFormat, taskFormat);
    }
}
