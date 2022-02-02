package task;

import main.Date;

import java.time.LocalDate;

/**
 * Represent an event, which is a type of task.
 * @author Fan Jue
 * @version 0.1.0
 * @since 0.1.0
 */
public class Event extends Task {
    /** String representation of the time the event taks place */
    protected String at;
    /** Date at which the event occurs */
    protected Date date;

    /**
     * Create an event which occurs on a specific date.
     *
     * @param description A description of the event.
     * @param at Date at which the event occurs.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.date = new Date(at);
    }

    /**
     * Convert the event to a string in the format of storage memory.
     *
     * @return Memory representation of the event.
     */
    @Override
    public String toMemoryString() {
        return "E" + super.toMemoryString()
                + "@" + this.at;
    }

    /**
     * Check if the event occurs on a specific date.
     *
     * @param date An input date to check with the instance.
     * @return True if the event occurs on the input date.
     */
    @Override
    public boolean isOn(Date date) {
        return this.date.equals(date);
    }

    /**
     * Convert the event to a string for UI.
     *
     * @return The UI representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (at: " + date.formattedTime() + ")";
    }
}
