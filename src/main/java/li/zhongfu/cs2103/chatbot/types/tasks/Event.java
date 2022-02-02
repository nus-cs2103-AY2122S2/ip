package li.zhongfu.cs2103.chatbot.types.tasks;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A representation of an event item in a task list, which also contains an event date and time.
 */
public class Event extends Task {
    private LocalDateTime eventTime;

    /**
     * Creates a new event item.
     * 
     * @param name the name of the event
     * @param eventTime the date and time of the event
     */
    public Event(String name, LocalDateTime eventTime) {
        super(name);
        this.eventTime = eventTime;
    }

    /**
     * Returns the date and time of the event.
     * 
     * @returns the date and time of the event
     */
    public LocalDateTime getEventTime() {
        return this.eventTime;
    }

    /**
     * Returns a string representation of this event item.
     * 
     * @returns a string representation of this event item
     */
    @Override
    public String toString() {
        return String.format(
                "[E][%s] %s (at: %s)", this.getDone() ? "X" : " ",
                this.getName(),
                this.getEventTime().format(DATE_TIME_FORMAT));
    }

    /**
     * Indicates whether the Object {@code o} is equal to this Event.
     * 
     * @param o the Object to compare this Event against
     * @returns true if {@code o} is equal to this Event, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Event) {
            Event event = (Event) o;
            return super.equals(event) && this.eventTime.equals(event.eventTime);
        }
        return false;
    }

    /**
     * Returns a hash code value for this Event.
     * 
     * @returns a hash code value for this Event
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.eventTime);
    }
}
