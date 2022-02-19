package duke;

import java.time.LocalDate;

/**
 * Represents a task that is an Event
 */
public class Event extends Task {
    /**
     * Constructor for Event.
     * @param description 
     * @param at the date of the event.
     */
    public Event(String description, LocalDate at) {
        super(description, at);
    }

    /**
     * Converts the event task to a string which consist of the status, description and date of the event.
     */
    @Override
    public String toString() {
        String eventString = "[E]" + this.getStatusIcon() + " "
                + this.description + " (at: " + Date.toString(this.date) + ")";

        return eventString;
    }
}
