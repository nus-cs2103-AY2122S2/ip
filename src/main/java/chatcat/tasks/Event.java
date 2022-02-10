package chatcat.tasks;

import chatcat.util.DateTimeUtil;

/**
 * The default Event class inherited from {@code Task}.
 *
 * @see Task
 * @see DateTimeUtil
 */
public class Event extends Task {
    String event;
    String time;

    /**
     * Creates a {@code Event} object using a specified description.
     *
     * @param event the description of this task.
     * @param time the time of the task.
     */
    public Event(String event, String time) {
        super(event);
        this.event = event;
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
}
