package mickey.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    public LocalDateTime at;

    public Event(String description, String date) throws DateTimeParseException {
        this.description = description;
        this.at = LocalDateTime.parse(date, (DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"))
                + ")";
    }
}
