package duke.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.exception.DukeException;

/**
 * Represents a Deadline Task that contains a due date.
 */
public class Deadline extends Task {
    private LocalDate dueDate;

    /**
     * A constructor that stores the name of the task and its due date.
     *
     * @param taskName The name of the task.
     * @param by       The String representation of the date
     * @throws DukeException If the date given is invalid or the format is incorrect.
     */
    public Deadline(String taskName, String by) throws DukeException {
        super(taskName);
        assert taskName.length() > 0;
        assert by.length() > 0;
        boolean isCorrectFormat = checkFormat(by);
        if (isCorrectFormat) {
            try {
                this.dueDate = LocalDate.parse(by);
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
     * @param dueDate  The LocalDate representation of the date.
     */
    public Deadline(String taskName, LocalDate dueDate) {
        super(taskName);
        assert taskName.length() > 0;
        this.dueDate = dueDate;
    }

    /**
     * Returns the String representation of the task.
     *
     * @return A String representation of the task.
     */
    @Override
    public String toString() {
        String done = isComplete() ? "[X]" : "[ ]";
        return "[D]" + done + getTaskName() + " (by: "
                + this.dueDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
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
     * Returns the LocalDate date of this Deadline.
     *
     * @return The LocalDate date of this Deadline.
     */
    public LocalDate getDate() {
        return this.dueDate;
    }
}
