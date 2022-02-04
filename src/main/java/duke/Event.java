package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This is a child class of Task, Event.
 * Event accepts another variable, 'at' that
 * stores the time this Event is taking place
 *
 * @author  Hsiao Jiet
 * @version 1.0
 * @since   2022-1-15
 */

public class Event extends Task {
    protected LocalDate at;

    /**
     * Creates Event object with provided LocalDate variable
     * @param name of the Task (description)
     * @param done whether the Task is completed or not
     * @param at the date when this Task is held on
     */
    public Event(String name, int done, LocalDate at) {
        super(name, done);
        super.type = 'E';
        this.at = at;
    }

    /**
     * Creates Event object without provided LocalDate variable
     * Used when there is the need to parse text into date first
     * @param name of the Task (description)
     * @param done whether the Task is completed or not
     */
    public Event(String name, int done) {
        super(name, done);
        super.type = 'E';
    }

    public void setAt(LocalDate at) {
        this.at = at;
    }

    /**
     * Setting Event object's date it is held on
     * @param date provided in the format of "yyyy/MM/dd" by user
     */
    public void setLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MMMM/dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        setAt(localDate);
    }

    /**
     * Converting LocalDate variable to String
     */
    public String convertLocalDateToString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MMMM/dd");
        return at.format(formatter);
    }

    /**
     * Producing a user-friendly view of an Event Task's information
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(getTaskIcon()).append(" - ");
        result.append(done).append(" - ");
        result.append(name).append(" - ");;
        result.append(convertLocalDateToString()).append("\n");
        return result.toString();
    }
}
