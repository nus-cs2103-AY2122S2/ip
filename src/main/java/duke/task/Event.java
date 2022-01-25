package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event, with a specific date and time.
 */
public class Event extends Task {

    protected LocalDateTime at;
    protected String type;

    /**
     * Class constructor.
     *
     * @param description the description of the event
     * @param at the date and time of the event
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
        this.type = "E";
    }

    /**
     * Returns the string representation of the event.
     *
     * @return The string representation of the status, description and datetime of event.
     */
    @Override
    public String toString() {
        String formattedDateTime = at.format(DateTimeFormatter.ofPattern("d MMM yyyy, h:mm a"));
        return "[E]" + super.toString() + " (at: " + formattedDateTime + ")";
    }

    /**
     * Returns the type of the event task.
     *
     * @return "E", which is the short notation of an event type.
     */
    @Override
    public String getType() {
        return this.type;
    }

    /**
     * Returns the description and datetime of the event.
     *
     * @return The string representation of the event of format: description | datetime
     */
    @Override
    public String getDescription() {
        return this.description + " | " + at.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
