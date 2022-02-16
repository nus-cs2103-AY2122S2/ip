package batman.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import batman.parser.DateUtil;

/**
 * A Task encapsulating an event with a date and time.
 */
public class Event extends Task {

    protected LocalDateTime at;

    /**
     * Creates an Event object which has date and time included.
     *
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
     * Creates an Event object which has date and time included.
     *
     * @param description Description of the task.
     * @param at Format: "yyyy-mm-dd hh:mm" or "dd/mm/yyyy hh:mm" (24h time).
     * @throws DateTimeParseException  If by is wrongly formatted.
     */
    public Event(String description, String at) throws DateTimeParseException {
        this(false, description, at);
    }

    /**
     * Returns Task's details to store in file.
     *
     * @return String object of task.
     */
    @Override
    public String appendToFile() {
        return "E|" + (super.isDone ? "1" : "0") + "|" + super.description + "|" + DateUtil.dateToString(at) + "\n";
    }

    /**
     * Returns details of task's date and time.
     *
     * @return LocalDateTime object
     */
    @Override
    public LocalDateTime getDateTime() {
        return at;
    }

    /**
     * Returns task's type.
     *
     * @return String object of task's type.
     */
    @Override
    public String taskType() {
        return "E";
    }

    /**
     * Checks if keyword is found in tasks.
     *
     * @param keyword Total number of tasks.
     * @return Boolean object of whether matched tasks exists.
     */
    @Override
    public boolean contains(String keyword) {
        String task = taskType() + description + DateUtil.dateToString(at);
        return task.toLowerCase().contains(keyword.toLowerCase());
    }

    /**
     * Returns status icon and description of tasks.
     *
     * @return String object of task's details.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + DateUtil.dateToString(at) + ")";
    }
}
