package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.exception.ErrorString;


/**
 * Event is a task that start at a specific time and ends at a specific time.
 *
 * @author yl-ang
 */
public class Event extends Task {

    protected LocalDate at;

    /**
     * Constructs an Event object containing the user specified description and date.
     *
     * @param description Description specified by the user.
     * @param at At date specified by the user.
     */
    public Event(String description, String at) {
        super(description);

        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            throw new DukeException(ErrorString.ERROR_INVALID_EVENT_DATE_FORMAT.toString());
        }

    }

    /**
     * Converts event object into string format for display.
     *
     * @return Formatted string of the event description and date.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }

    /**
     * Converts event object into string format for storage in duke.txt.
     *
     * @return Formatted string of the event description and date for storage in duke.txt.
     */
    @Override
    public String toStringForStorage() {
        int isDone = super.isDone ? 1 : 0;
        return "E | " + isDone + " | " + this.description + " | " + this.at;
    }

    @Override
    public void updateDate(String date) {
        try {
            this.at = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new DukeException(ErrorString.ERROR_INVALID_DEADLINE_DATE_FORMAT.toString());
        }
    }
}
