import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// STRETCH GOAL: Add a duration for event?
public class Event extends Task {
    private static final DateTimeFormatter outputFormatter =
            DateTimeFormatter.ofPattern("MMM d yyyy hh:mma");
    private final LocalDateTime eventDate;

    public Event(String description, LocalDateTime eventDate) {
        super(description);
        this.eventDate = eventDate;
    }

    public Event(String description, String eventDate) {
        super(description);
        this.eventDate = LocalDateTime.parse(eventDate, outputFormatter);
    }

    @Override
    public String getSaveFormat() {
        return super.getSaveFormat() + SAVE_SEPARATOR + eventDate.format(outputFormatter);
    }

    @Override
    public String getIcon() {
        return "E";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (at: %s)", eventDate.format(outputFormatter));
    }
}
