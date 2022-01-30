package apollo.tasks;

import apollo.parser.Parser;

import java.time.LocalDateTime;

/**
 * Class for {@code Event} tasks.
 * Extends {@code Task} superclass.
 */
public class Event extends Task {

    private final LocalDateTime period;

    /**
     * Constructor for {@code Event}.
     *
     * @param description Of task.
     * @param period Date and Time of Event.
     */
    public Event(String description, LocalDateTime period) {
        super(description, Type.EVENT);
        this.period = period;
    }

    /**
     * Adds String representation of date and time of event to task.
     *
     * @return String representation of object.
     */
    @Override
    public String toString() {
        return String.format("%s (at: %s)",
                super.toString(),
                this.period.format(Parser.formatter));
    }
}
