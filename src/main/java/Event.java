import java.time.LocalDateTime;

public class Event extends Task {
    private final LocalDateTime period;

    public Event(String description, LocalDateTime period) {
        super(description);
        this.period = period;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.period);
    }
}
