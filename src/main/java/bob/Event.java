package bob;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends bob.Task {
    protected LocalDateTime time;

    public Event(String description, String time) {
        super(description);
        this.time = LocalDateTime.parse(time);
    }

    public Event(String description, String time, boolean isDone) {
        super(description, isDone);
        this.time = LocalDateTime.parse(time);
    }

    @Override
    public String generateSavedEntry() {
        return String.format("E|%s|%s", isDone ? "1" : "0", description + "/at "
                + time.format(DateTimeFormatter.ISO_DATE_TIME));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(),
                time.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
