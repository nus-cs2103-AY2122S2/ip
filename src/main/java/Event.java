import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event, is a subclass of task.
 */
public class Event extends Task{
    protected LocalDateTime at;

    public Event(String description, String at) {
        super(description);
        this.at = LocalDateTime.parse(at, DateTimeFormatter.ofPattern("[d/M/y HHmm]"));
    }

    public Event(String description, String at, boolean isDone) {
        super(description,isDone);
        this.at = LocalDateTime.parse(at, DateTimeFormatter.ofPattern("[d/M/y HHmm]"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d y - HHmm")) + ")";
    }
}
