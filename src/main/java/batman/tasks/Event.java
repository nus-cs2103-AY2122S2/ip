package batman.tasks;

import batman.parser.DateUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * A Task encapsulating an event with a date and time.
 */
public class Event extends Task {

    protected LocalDateTime at;

    /**
     * @param isDone Whether the task is done.
     * @param description Description of the task.
     * @param at Format: "yyyy-mm-dd hh:mm" or "dd/mm/yyyy hh:mm" (24h time).
     * @throws DateTimeParseException  If by is wrongly formatted.
     */
    public Event(boolean isDone, String description, String at) throws DateTimeParseException {
        super(description);
        this.at = DateUtil.stringToDate(at);
        this.isDone = isDone;
    }

    /**
     * @param description Description of the task.
     * @param by Format: "yyyy-mm-dd hh:mm" or "dd/mm/yyyy hh:mm" (24h time).
     * @throws DateTimeParseException  If by is wrongly formatted.
     */
    public Event(String description, String by) throws DateTimeParseException {
        this(false, description, by);
    }

    @Override
    public String appendtoFile() {
        return "E|" + (super.isDone ? "1" : "0") + "|" + super.description + "|" + DateUtil.dateToString(at) + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + DateUtil.dateToString(at) + ")";
    }
}
