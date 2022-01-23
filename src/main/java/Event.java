import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        String formattedDateTime = at.format(DateTimeFormatter.ofPattern("d MMM yyyy, h:mm a"));
        return "[E]" + super.toString() + " (at: " + formattedDateTime + ")";
    }
}
