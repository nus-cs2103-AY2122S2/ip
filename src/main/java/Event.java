import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
    protected LocalDateTime time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + time + ")";
    }
}
