package duke;
import java.time.format.DateTimeFormatter;

/**
 * Event class to store event type of task.
 */
public class Event extends Task {

    protected String type;

    /**
     * Construct Event object.
     *
     * @param description Description of task.
     * @param at    When the task is happening.
     */
    public Event(String description, String at) {
        super(description, at);
        this.type = "E";
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + super.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
