import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime at;

    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = LocalDateTime.parse(at, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    public Event(String description, String at) {
        this(description, at, false);
    }

    @Override
    public String save() {
        String dateTime = at.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        return "E | " + super.save() + " | " + dateTime + System.lineSeparator();
    }

    @Override
    public String toString() {
        String dateTime = at.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
        return "[E]" + super.toString() + " (at: " + dateTime + ")";
    }
}
