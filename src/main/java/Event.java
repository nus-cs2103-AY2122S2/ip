import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    public LocalDateTime atTime;

    public Event(String description, boolean isMarked, LocalDateTime atTime) {
        super(description, isMarked);
        this.atTime = atTime;
    }

    @Override
    public String toString() {
        String atTimeFormatted = this.atTime.format(DateTimeFormatter.ofPattern("HH:mm, MMM dd yyyy"));
        return "[E]" + super.toString() + " (at: " + atTimeFormatted + ")";
    }

    @Override
    public String toData() {
        return "E | " + this.isMarked + " | " + this.description + " | " + this.atTime;
    }
}
