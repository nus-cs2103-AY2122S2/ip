package duke;

import java.time.LocalDateTime;

/**
 * The type Event.
 */
public class Event extends Task {

    protected LocalDateTime by;

    /**
     * Instantiates a new Event.
     *
     * @param description the description
     * @param by          the deadline
     */
    public Event(String description, LocalDateTime by) {
        super(description, "E");
        this.by = by;
    }

    /**
     * Instantiates a new Event.
     *
     * @param description the description
     * @param by          the deadline
     * @param done        the done
     */
    public Event(String description, LocalDateTime by, boolean done) {
        super(description, done, "E");
        this.by = by;
    }

    @Override
    public LocalDateTime getBy() {
        return this.by;
    }
    @Override
    public Task mark() {
        return new Event(this.desc, this.by, true);
    }
    @Override
    public Task unmark() {
        return new Event(this.desc, this.by, false);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (by: " + by + ")";
    }
}
