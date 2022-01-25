package Duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.Serializable;

public class Event extends Task implements Serializable {
    protected LocalDate at;

    public Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at);
    }

    public LocalDate getDate() {
        return this.at;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + super.getStatusIcon() + "] " + super.getDescription() +
                "at (" + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}