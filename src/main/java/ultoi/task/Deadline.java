package ultoi.task;

import ultoi.util.DateTime;
import ultoi.util.UltoiException;

/**
 * Represents a deadline task.
 *
 * @author snoidetx
 * @version 0.0.0
 */
public class Deadline extends Task {
    protected DateTime dateTime;

    /**
     * Creates a deadline.
     *
     * @param description Description of the task.
     * @param time Due date of the task.
     * @throws UltoiException If due date cannot be recognized.
     */
    public Deadline(String description, String time) throws UltoiException {
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
        return "deadline " + description + " /by " + dateTime.toInputString();
    }

    /**
     * Returns a string representation of the deadline.
     *
     * @return String representation of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dateTime.toString() + ")";
    }

    /**
     * Returns the date and time of the task.
     *
     * @return Date and time.
     */
    public DateTime getDateTime() {
        return dateTime;
    }
}
