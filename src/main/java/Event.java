import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime time;

    Event(String description, String time) {
        super(description);
        this.time = LocalDateTime.parse(time);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.time);
    }
}
