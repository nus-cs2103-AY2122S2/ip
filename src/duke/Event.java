package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This is a child class of duke.Task, duke.Event.
 * duke.Event accepts another variable, 'at' that
 * stores the time this duke.Event is taking place
 *
 * @author  Hsiao Jiet
 * @version 1.0
 * @since   2022-1-15
 */

public class Event extends Task {
    protected LocalDate at;

    /**
     * Creates duke.Event object with provided LocalDate variable
     */
    public Event(String name, int isDone, LocalDate at) {
        super(name, isDone);
        super.type = 'E';
        this.at = at;
    }

    /**
     * Creates duke.Event object without provided LocalDate variable
     * Used when there is the need to parse text into date first
     */
    public Event(String name, int isDone) {
        super(name, isDone);
        super.type = 'E';
    }

    public void setAt(LocalDate at) {
        this.at = at;
    }

    /**
     * Setting duke.Event object's date it is held on
     */
    public void setStringToLocalDate(String date) {
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
     * Producing a user-friendly view of an duke.Event task
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(getTaskIcon()).append(" - ");
        result.append(isDone).append(" - ");
        result.append(name).append(" - ");;
        result.append(convertLocalDateToString()).append("\n");
        return result.toString();
    }
}
