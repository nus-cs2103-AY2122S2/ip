package duke.task;

import static duke.constant.Message.CLOSE_BRACKET;
import static duke.constant.Message.OPEN_BRACKET;
import static duke.constant.TaskConstant.PREFIX_EVENT;

/**
 * A class represents for an event.
 */
public class Event extends Task{
    protected String at;

    /**
     * Class constructor with the description and the date time
     * Creates an undone Event.
     *
     * @param description Event description
     * @param at Event date time
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the date time of Event
     * @return Event date time
     */
    public String getAt() {
        return at;
    }

    /**
     * Overrides toString method to make a string including prefix, status icon, description and date.
     * @return String representation of Event
     */
    @Override
    public String toString() {
        return OPEN_BRACKET + PREFIX_EVENT + CLOSE_BRACKET + super.toString() + " (at: " + at + ")";
    }
}
