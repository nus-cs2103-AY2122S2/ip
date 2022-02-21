package ultoi.task;

import ultoi.util.DateTime;
import ultoi.util.UltoiException;

/**
 * Represents a event.
 *
 * @author snoidetx
 * @version 0.1.0
 */
public class Event extends Task {
    protected DateTime dateTime;

    /**
     * Creates a new event.
     *
     * @param description Description of the event.
     * @param time Date and time of the event.
     * @throws UltoiException If the input date and time cannot be recognized.
     */
    public Event(String description, String time) throws UltoiException {
        super(description);
        try {
            this.dateTime = new DateTime(time);
        } catch (UltoiException e) {
            throw e;
        }
    }

    /**
     * Returns the standard input message to create this task.
     *
     * @return Input string.
     */
    public String toInputString() {
        return "event " + description + " /at " + dateTime.toInputString();
    }

    /**
     * Returns a string representation of the event.
     *
     * @return String representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.dateTime.toString() + ")";
    }

    /**
     * Returns the date and time of the task.
     *
     * @return Date and time.
     */
    @Override
    public DateTime getDateTime() {
        return dateTime;
    }
}
