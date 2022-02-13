package luke.data.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.function.Function;

import luke.parser.DateTimeParser;

/**
 * Implements a Recurring task.
 */
public abstract class RecurringTask extends Task {

    protected String every;
    protected boolean isNew;
    protected LocalDateTime recurDate;
    protected Function<LocalDateTime, LocalDateTime> recurFunction;

    /**
     * Constructs a recurring task.
     *
     * @param every  The recurring duration.
     * @param next   The next date to recur from.
     * @throws DateTimeParseException If the every argument or datetime is not one of the
     * format accepted by {@code DateTimeParser}
     */
    public RecurringTask(String description, String every, String next) throws DateTimeParseException {
        super(description);
        this.every = every.stripTrailing();
        this.recurDate = DateTimeParser.toLocalDateTime(next.stripTrailing());
        this.recurFunction = DateTimeParser.getDateTimeIncrementFunction(every.trim());
        updateRecurDate();
    }


    private void updateRecurDate() {
        LocalDateTime oldDate = recurDate;
        while (recurDate.isBefore(LocalDateTime.now())) {
            recurDate = recurFunction.apply(recurDate);
        }
        isNew = !oldDate.isEqual(recurDate);
    }

    protected String getDateAsCommandString() {
        return DateTimeParser.toCommandString(recurDate);
    }

    @Override
    public void setDoneStatus(int value) {
        if (isNew) {
            isNew = false;
        } else {
            super.setDoneStatus(value);
        }
    }

    @Override
    public String toString() {
        return "[R]%s" + super.toString() + "%s (every: " + every + ")";
    }

    @Override
    public String getCommandString() {
        return "recur %s" + String.format(" /every %s /next %s", every, getDateAsCommandString());
    }
}
