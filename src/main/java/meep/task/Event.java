package meep.task;

import java.time.LocalDateTime;

import meep.parser.Parser;


/**
 * Represents the event task.
 */
public class Event extends Task {
    protected LocalDateTime on;

    /**
     * Constructor for class Event.
     *
     * @param title title of event task.
     * @param on    deadline of the task.
     */
    public Event(String title, LocalDateTime on) {
        super(title);
        this.on = on;
    }

    /**
     * Constructor for class Event.
     *
     * @param title title of event task.
     * @param done  status of the task.
     * @param on    deadline of the task.
     */
    public Event(String title, boolean done, LocalDateTime on) {
        super(title, done);
        this.on = on;
    }

    /**
     * Gets datetime.
     *
     * @return the datetime.
     */
    public LocalDateTime getDate() {
        return on;
    }

    @Override
    public String toString() {
        return "[ E ]" + super.toString() + " (on: " + Parser.printDate(on) + ")";
    }
}
