package chatcat.tasks;

import chatcat.util.DateTimeUtil;

/**
 * The default Event class inherited from {@code Task}.
 *
 * @see Task
 * @see DateTimeUtil
 */
public class Event extends Task {
    String eventStr;
    String time;

    /**
     * Creates a {@code Event} object using a specified description.
     *
     * @param eventStr the description of this task.
     * @param time the time of the task.
     */
    public Event(String eventStr, String time) {
        super(eventStr);
        this.eventStr = eventStr;
        this.time = time;
    }

    /**
     * Returns a representation in string of {@code Event} task.
     *
     * @return a representation in string of {@code Event} task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + time + ")";
    }

    /**
     * Checks if this event {@code Event} instance is the same as
     * another event {@code Event} instance.
     *
     * @param o object {@code Object} to compare with this event {@code Event} instance.
     * @return true if parameter has the same description as this
     * event {@code event} instance.
     */
    @Override
    public boolean equals(Object o) {

        if (o == null) {
            return false;
        }

        if (!(o instanceof Event)) {
            return false;
        }

        if (o == this) {
            return true;
        }

        Event event = (Event) o;
        boolean isEventEqual;
        boolean isDateEqual = this.time.equals(event.time);
        boolean isEventTypeEqual = this.eventStr.equals(event.eventStr);
        isEventEqual = isDateEqual && isEventTypeEqual;

        return isEventEqual;
    }
}
