package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

/**
 * Represents a task with a time to do it at. A <code>Event</code> object corresponds to the task represented by
 * a String name and a String at which specified the time to do it at.
 */
public class Event extends duke.task.Task {
    protected LocalDate at;

    /**
     * Returns a new instance of a <code>Event</code> object with the specified name and time to do it at.
     *
     * @param name Name of the event task.
     * @param at   Time to do the Event task at.
     */
    public Event(String name, String at) throws DateTimeParseException {
        super(name);
        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException storedException) {
            this.at = LocalDate.parse(at, DateTimeFormatter.ofLocalizedDate(FormatStyle.valueOf("MMM dd yyyy")));
        }
    }

    /**
     * Returns a String representation of the <code>Event</code> object to be read by the users in <code>Duke</code>.
     *
     * @return User-friendly string representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /**
     * Returns a String representation of the <code>Event</code> object to be saved in the hard drive for future
     * uses. It is more concise and computer-friendly than the <code>toString</code> method.
     *
     * @return Computer-friendly string representation of the event for storing of data.
     */
    @Override
    public String toText() {
        return "E | " + (this.getIsDone() ? 1 : 0) + " | " + this.getName() + " | " + this.at + "\n";
    }

    @Override
    public LocalDate getDate() {
        return this.at;
    }
}
