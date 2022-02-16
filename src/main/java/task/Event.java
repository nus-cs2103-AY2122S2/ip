package task;

import exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Class that encapsulates event tasks with due dates.
 */
public class Event extends Task {

    private LocalDate date;

    /**
     * Event class constructor
     * @param details provides description of the event being created.
     * @param date provides the date of when the event is.
     */
    public Event(String details, String date) throws DukeException {
        super(details);
        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new DukeException("Hmm...have you entered a correct date?");
        }
    }

    /**
     * Returns character symbol that represents event tasks.
     * @return String 'E'.
     */
    public String symbol() {
        return "E";
    }

    /**
     * Returns event details along with full string representation of the date.
     * @return details of the event.
     */
    @Override
    public String displayTime() {
        return super.toString() + this.date.getDayOfMonth() + " " + this.date.getMonth()
                + " " + this.date.getYear();
    }

    /**
     * Returns details of the event along with date object.
     * @return String which combines event details with toString method of the date object.
     */
    @Override
    public String toString() {
        return super.toString() + "/" + this.date.toString();
    }

}
