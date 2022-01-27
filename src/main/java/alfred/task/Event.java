package alfred.task;

import java.time.LocalDateTime;

/**
 * Encapsulates the event task.
 */
public class Event extends Task {
    String type = "E";
    LocalDateTime dateTime;

    /**
     * Constructs an Event object.
     *
     * @param description String name of the event.
     * @param dateAndTime Date and time of the event in ISO format.
     */
    public Event(String description, String dateAndTime) {
        super(description);
        this.dateTime = LocalDateTime.parse(dateAndTime);
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + super.toString() + " (at: "
                + super.localDateTimeToString(this.dateTime) + ")";
    }
}