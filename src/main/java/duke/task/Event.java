package duke.task;

import duke.exception.DukeException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event Task that contains a date.
 */
public class Event extends Task{
    LocalDate date;

    /**
     * A constructor that stores the name of the task and its date.
     *
     * @param taskName The name of the task.
     * @param at The String representation of the date
     * @throws DukeException If the date given is invalid or the format is incorrect.
     */
    public Event(String taskName, String at) throws DukeException {
        super(taskName);
        boolean isCorrectFormat = formatChecker(at);
        if (isCorrectFormat) {
            try {
                this.date = LocalDate.parse(at);
            } catch (DateTimeException e) {
                throw new DukeException("Invalid date");
            }
        } else {
            throw new DukeException("Invalid time format, please use yyyy-mm-dd");
        }
    }

    /**
     * Returns the String representation of the task.
     *
     * @return A String representation of the task.
     */
    @Override
    public String toString() {
        String done = isComplete() ? "[X]" : "[ ]";
        return "[E]" + done + getTaskName() + " (at: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /**
     * Returns a boolean depending on whether the input String follows the date format.
     *
     * @param date String representation of the date, in yyyy-mm-dd format.
     * @return A boolean depending on whether the String follow the correct format.
     */
    public static boolean formatChecker(String date) {
        if (date.length() != 10) {
            return false;
        } else if (date.charAt(4) != '-' || date.charAt(7) != '-') {
            return false;
        } else {
            return true;
        }
    }
}
