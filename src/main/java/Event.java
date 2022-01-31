import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDateTime setAt;

    public Event(String description, LocalDateTime setAt) {
        super(description);
        this.setAt = setAt;
    }

    @Override
    public String toString() {
        return String.format("[E]%s(at: %s)", super.toString(), setAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }
}
