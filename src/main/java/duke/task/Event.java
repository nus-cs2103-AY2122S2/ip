package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime at;
    protected String type;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
        this.type = "E";
    }

    @Override
    public String toString() {
        String formattedDateTime = at.format(DateTimeFormatter.ofPattern("d MMM yyyy, h:mm a"));
        return "[E]" + super.toString() + " (at: " + formattedDateTime + ")";
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getDescription() {
        return this.description + " | " + at.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
