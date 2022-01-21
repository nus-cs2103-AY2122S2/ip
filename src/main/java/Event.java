import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDate time;

    public Event(String taskName, String time) {
        super(taskName);
        this.time = LocalDate.parse(time);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (at: %s)",
                this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
