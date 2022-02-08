package ultoi.task;

import ultoi.command.Command;
import ultoi.command.AddCommand;
import ultoi.command.ByeCommand;
import ultoi.command.DeleteCommand;
import ultoi.command.ListCommand;
import ultoi.command.MarkCommand;

import ultoi.util.Ultoi;
import ultoi.util.UltoiUi;
import ultoi.util.UltoiException;
import ultoi.util.Storage;
import ultoi.util.TaskList;
import ultoi.util.Parser;
import ultoi.util.DateTime;

/**
 * Represents a event.
 *
 * @author snoidetx
 * @version 0.0.0
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
}
