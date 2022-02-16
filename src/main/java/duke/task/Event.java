package duke.task;

import duke.DukeDateTime;

/**
 * Task of type Event.
 */
public class Event extends Task {

    protected static final Icon ICON = Icon.E;
    protected final DukeDateTime at;

    /**
     * Constructs an {@code Event} object with the specified description and time
     * @param description the description of the event
     * @param at a {@code DukeDateTime} object specifying the time of the event
     */
    public Event(String description, DukeDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Constructs a {@code Deadline} object with the specified description, status and time
     * @param description the description of the event
     * @param isDone a boolean indicating whether the task is done
     * @param at a {@code DukeDateTime} object specifying the time of the event
     */
    public Event(String description, boolean isDone, DukeDateTime at) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[" + ICON + "]" + super.toString() + " (at: " + at.format("d MMM yyyy") + ")";
    }

    @Override
    public String toStringRecord() {
        return ICON + " " + super.toStringRecord() + " /at " + at.format("yyyy-M-d");
    }

    @Override
    public Icon getIcon() {
        return ICON;
    }

    @Override
    public Event mark() {
        return new Event(description, true, at);
    }

    @Override
    public Event unmark() {
        return new Event(description, false, at);
    }

}
