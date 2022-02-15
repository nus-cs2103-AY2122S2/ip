package duke.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.exception.DukeException;

/**
 * Represents an Event Task that contains a date.
 */
public class Event extends Task {
    private LocalDate date;

    /**
     * A constructor that stores the name of the task and its date.
     *
     * @param taskName The name of the task.
     * @param at The String representation of the date
     * @throws DukeException If the date given is invalid or the format is incorrect.
     */
    public Event(String taskName, String at) throws DukeException {
        super(taskName);
        assert taskName.length() > 0;
        assert at.length() > 0;
        boolean isCorrectFormat = checkFormat(at);
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
     * A constructor that stores the name of the task and the due date in a LocalDate format.
     *
     * @param taskName The name of the task.
     * @param date The LocalDate representation of the date.
     */
    public Event(String taskName, LocalDate date) {
        super(taskName);
        assert taskName.length() > 0;
        this.date = date;
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
    public static boolean checkFormat(String date) {
        if (date.length() != 10) {
            return false;
        } else if (date.charAt(4) != '-' || date.charAt(7) != '-') {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns the LocalDate date of this Event.
     *
     * @return The LocalDate date of this Event.
     */
    public LocalDate getDate() {
        return this.date;
    }
}
