package arthur.task;

import arthur.timings.DateTime;

/**
 * A class that creates Task.Event objects with at variable,
 * that stores the data/time the event will occur.
 */
public class Event extends Task {
    private final String at;
    private final DateTime timings;

    /**
     * Constructor for event objects
     * @param e String to be created as Task
     * @param at String with the necessary timing info
     */
    public Event(String e, String at) {
        super(e);
        timings = new DateTime(at);
        this.at = timings.getString();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(At: " + this.at + ")";
    }
}
