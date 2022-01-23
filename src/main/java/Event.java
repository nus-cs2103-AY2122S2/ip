import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    LocalDateTime time;
    public Event(String description, LocalDateTime time) {
        super(description, TaskType.EVENT);
        this.time = time;
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return String.format("%s (at: %shrs)", super.toString(), this.time.format(format));
    }
}
