package tesseract.task;

import tesseract.main.Date;

/**
 * Represent an event, which is a type of task.
 * @author Fan Jue
 * @version 0.1.0
 * @since 0.1.0
 */
public class Event extends Task {
    private static final String MEMORY_FORMAT = "E@%s@%s";
    private static final String STRING_FORMAT = "[E]%s (at: %s)";
    // String representation of the time the event tasks place
    private final String at;
    // Date at which the event occurs
    private final Date date;

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
        String output = String.format(MEMORY_FORMAT, super.toMemoryString(), this.at);
        return archiveString(output);
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
        return String.format(STRING_FORMAT, super.toString(), date.formattedTime());
    }
}
