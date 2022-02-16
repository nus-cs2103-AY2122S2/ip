package mytasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * * Creates a task that will begin at a certain time.
 */
public class Event extends Task {
    private String dateTime;

    /**
     * Constructor for a event task.
     * @param description The description of a event.
     * @param dateTime The date and time of a event.
     */
    public Event(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Return the dat and time of an event.
     * @return date and time of an event.
     */
    public String returnDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(by: "
                + LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd hha"))
                        .format(DateTimeFormatter.ofPattern("MMMM d, yyyy hha")) + ")";
    }
}
