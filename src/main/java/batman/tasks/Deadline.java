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
     * Creates a Deadline object which has date and time included.
     *
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
     * Creates a Deadline object which has date and time included.
     *
     * @param description Description of the task.
     * @param by Format: "yyyy-mm-dd hh:mm" or "dd/mm/yyyy hh:mm" (24h time).
     * @throws DateTimeParseException  If by is wrongly formatted.
     */
    public Deadline(String description, String by) throws DateTimeParseException {
        this(false, description, by);
    }

    /**
     * Returns Task's details to store in file.
     *
     * @return String object of task.
     */
    @Override
    public String appendToFile() {
        return "D|" + (super.isDone ? "1" : "0") + "|" + super.description + "|" + DateUtil.dateToString(by) + "\n";
    }

    /**
     * Returns details of task's date and time.
     *
     * @return LocalDateTime object
     */
    @Override
    public LocalDateTime getDateTime() {
        return by;
    }

    /**
     * Returns task's type.
     *
     * @return String object of task's type.
     */
    @Override
    public String taskType() {
        return "D";
    }

    /**
     * Checks if keyword is found in tasks.
     *
     * @param keyword Total number of tasks.
     * @return Boolean object of whether matched tasks exists.
     */
    @Override
    public boolean contains(String keyword) {
        String task = taskType() + description + DateUtil.dateToString(by);
        return task.toLowerCase().contains(keyword.toLowerCase());
    }

    /**
     * Returns status icon and description of tasks.
     *
     * @return String object of task's details.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateUtil.dateToString(by) + ")";
    }
}
