package sparrow.logic.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import sparrow.model.Priority;
import sparrow.model.Status;

/**
 * Represents an event with a date and time.
 */
public class Event extends Task {
    protected final LocalDateTime at;

    /**
     * Constructs an event.
     * @param d The event description.
     * @param s The event status.
     * @param p The event priority.
     * @param a The event datetime.
     */
    public Event(String d, Status s, Priority p, String a) {
        super(d, s, p);
        at = parseDateTime(a);
    }

    /**
     * Constructs an event with unspecified status and priority, which defaults to NOT_DONE and MEDIUM respectively.
     * @param d The event description.
     * @param a The event datetime.
     */
    public Event(String d, String a) {
        super(d);
        at = parseDateTime(a);
    }

    /**
     * Returns the event as a string which can be saved and loaded as an event again.
     * @return Event as a string which can be saved and loaded as an event again.
     */
    @Override
    public String save() {
        String dateTime = at.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        return "E | " + super.save() + " | " + dateTime + " | " + savePriority() + "\n";
    }

    /**
     * Returns the event as a readable string.
     * @return Event as a readable string.
     */
    @Override
    public String toString() {
        String dateTime = at.format(DateTimeFormatter.ofPattern("MMM d HHmm"));
        return "[E] " + super.toString() + " (at " + dateTime + "H)\n";
    }
}
