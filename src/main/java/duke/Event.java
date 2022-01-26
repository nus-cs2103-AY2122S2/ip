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

    /**
     * Returns a String of the description of the task.
     *
     * @return  description of the task
     */
    public String getDescription() {
        return super.description;
    }

    @Override
    public String getType() {
        return this.type;
    }

    /**
     * Return the description and date of the task.
     *
     * @return  Description and date of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + super.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
