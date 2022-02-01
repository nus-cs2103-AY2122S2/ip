package batman.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import batman.parser.DateUtil;

/**
 * A task that has the date and time to indicate its deadline
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * @param isDone Whether the task is done.
     * @param description Description of the task.
     * @param by Format: "yyyy-mm-dd hh:mm" or "dd/mm/yyyy hh:mm" (24h time).
     * @throws DateTimeParseException  If by is wrongly formatted.
     */
    public Deadline(boolean isDone, String description, String by) throws DateTimeParseException {
        super(description);
        this.by = DateUtil.stringToDate(by);
        this.isDone = isDone;
    }

    /**
     * @param description Description of the task.
     * @param by Format: "yyyy-mm-dd hh:mm" or "dd/mm/yyyy hh:mm" (24h time).
     * @throws DateTimeParseException  If by is wrongly formatted.
     */
    public Deadline(String description, String by) throws DateTimeParseException {
        this(false, description, by);
    }

    @Override
    public String appendToFile() {
        return "D|" + (super.isDone ? "1" : "0") + "|" + super.description + "|" + DateUtil.dateToString(by) + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateUtil.dateToString(by) + ")";
    }
}
