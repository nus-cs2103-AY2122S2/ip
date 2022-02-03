package baron.tasks;

import java.time.LocalDateTime;

import baron.util.DateTimeUtil;

/**
 * Represents a task to attend an event that consists of one additional characteristic: 'at' when
 * the event should be attended. Inherited from {@code Task}.
 *
 * @see Task
 */
public class Event extends Task {
    private final LocalDateTime at;

    /**
     * Constructs a {@code Event} object with the specified description and date/time (at).
     *
     * @param description the description of this task.
     * @param at          the date/time of LocalDateTime type that the task should be done at.
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the string representation of this {@code Event} task.
     *
     * @return the string representation of this {@code Event} task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + DateTimeUtil.getDisplayString(this.at) + ")";
    }

    /**
     * Returns the encoded string of this {@code Event} task to be stored in the storage
     * using the specified delimiter.
     *
     * @param delimiter the string to be inserted in between various information of this task.
     * @return the encoded string of this {@code Event} task to be stored in the storage
     * using the specified delimiter.
     */
    @Override
    public String toSaveString(String delimiter) {
        return "E" + delimiter + super.toSaveString(delimiter) + delimiter + DateTimeUtil.getSaveString(this.at);
    }
}
