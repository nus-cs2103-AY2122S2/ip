import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    public LocalDateTime atTime;

    public Event(String description, LocalDateTime atTime) {
        super(description);
        this.atTime = atTime;
    }

    @Override
    public String toString() {
        String atTimeFormatted = this.atTime.format(DateTimeFormatter.ofPattern("HH:mm, MMM dd yyyy"));
        return "[E]" + super.toString() + " (at: " + atTimeFormatted + ")";
    }
}
