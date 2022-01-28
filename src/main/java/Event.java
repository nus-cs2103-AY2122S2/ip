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
     */
    public Event(String n, int d, LocalDate a) {
        super(n, d);
        super.type = 'E';
        at = a;
    }

    /**
     * Creates Event object without provided LocalDate variable
     * Used when there is the need to parse text into date first
     */
    public Event(String n, int d) {
        super(n, d);
        super.type = 'E';
    }

    public void setAt(LocalDate at) {
        this.at = at;
    }

    /**
     * Setting Event object's date it is held on
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
     * Producing a user-friendly view of an Event task
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(getTaskIcon()).append(" - ");
        res.append(this.getDoneIcon()).append(" - ");
        res.append(this.name).append(" - ");;
        res.append(convertLocalDateToString()).append("\n");
        return res.toString();
    }
}
