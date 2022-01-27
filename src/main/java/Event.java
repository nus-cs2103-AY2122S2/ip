import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate event_time;

    public Event(String description, LocalDate event_time) {
        super(description);
        this.event_time = event_time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + event_time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}