import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulate information of event task.
 */
public class Event extends Task {

    /**
     * Normal constructor.
     */
    public Event(String description, LocalDateTime at) {
        super(description, at);
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "[E]" + super.toString() + " (at: " + dtf.format(super.dateTime) + ")";
    }
}
