package task;

import exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Class that encapsulates deadline tasks with due dates.
 */
public class Deadline extends Task {

    private LocalDate date;

    /**
     * Deadline class constructor
     * @param details provides description of the deadline being created.
     * @param date provides the date when the deadline is due.
     * @throws DukeException when user has not entered a valid date.
     */
    public Deadline(String details, String date) throws DukeException {
        super(details);
        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new DukeException("Hmm...have you entered a correct date?");
        }
    }

    /**
     * Returns character symbol that represents deadline tasks.
     * @return String 'D'.
     */
    public String symbol() {
        return "D";
    }

    /**
     * Returns deadline details along with full string representation of the date.
     * @return details of the deadline.
     */
    @Override
    public String displayTime() {
        return super.toString() + this.date.getDayOfMonth() + " " + this.date.getMonth() + " " + this.date.getYear();
    }

    /**
     * Returns details of the deadline along with date object.
     * @return String which combines deadline details with toString method of the date object.
     */
    @Override
    public String toString() {
        return super.toString() + "/" + this.date.toString();
    }

}
