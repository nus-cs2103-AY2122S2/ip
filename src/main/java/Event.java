import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class represents the Event task
 *
 * @author Jan
 * @version 1.0
 */
public class Event extends Task {
    /**
     * The time of this event
     */
    private LocalDate eventTime;

    /**
     * Constructor for Event objects
     *
     * @param eventName  the event name
     */
    public Event(String eventName, LocalDate time) {
        super(eventName);
        this.eventTime = time;
    }

    /**
     * Returns a String representation of the Event
     *
     * @return  Event in String
     */
    @Override
    public String toString() {
        String box1 = "[E]";
        String doneness;
        if (super.getDone()) {
            doneness = "[X] ";
        } else {
            doneness = "[ ] ";
        }
        String eventName = super.toString();
        String time = " (at: " + eventTimeToString()+ ")";
        return box1 + doneness + eventName + time;
    }

    /**
     * Returns a string representation of the event time
     *
     * @return  event time in string
     */
    private String eventTimeToString() {
        return eventTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
