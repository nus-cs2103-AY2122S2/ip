package duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime at;

    /**
     * Event task constructor
     *
     * @param description Description of event
     * @param at Date and time of event
     * @param done Status of task
     */
    public Event(String description, LocalDateTime at, boolean done) {
        super(description,done);
        this.at = at;
    }
    /**
     * Parse date into MMM dd yyyy format
     *
     * @return date in MMM dd yyyy hh:mm a format
     */
    public String parseDate() {
        return this.at.format(DateTimeFormatter.ofPattern("MMM dd yyyy 'at' hh:mm a"));
    }

    /**
     * Overrides toString method
     *
     * @return description of task and date
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " ( " + parseDate() + ")";
    }

}
