package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a time to do it at. A <code>Event</code> object corresponds to the task represented by
 *  a String name and a String at which specified the time to do it at.
 */
public class Event extends duke.task.Task {
    protected String at;

    /**
     * Returns a new instance of a <code>Event</code> object with the specified name and time to do it at.
     * @param name Name of the event task.
     * @param at Time to do the Event task at.
     */
    public Event(String name, String at) {
        super(name);
        try {
            LocalDate d1 = LocalDate.parse(at);
            this.at = d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException ex) {
            this.at = at;
        }
    }

    /**
     * Returns a String representation of the <code>Event</code> object to be read by the users in <code>Duke</code>.
     * @return User-friendly string representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (by:" + at + ")";
    }

    /**
     * Returns a String representation of the <code>Event</code> object to be saved in the hard drive for future
     * uses. It is more concise and computer-friendly than the <code>toString</code> method.
     * @return Computer-friendly string representation of the event for storing of data.
     */
    @Override
    public String toText() {
        return "E | " + (this.getDone() ? 1 : 0) + " | " + this.getName() + " | " + this.at + "\n";
    }
}
