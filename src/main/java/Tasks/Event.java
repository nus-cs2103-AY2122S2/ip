package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate duration;

    public Event(String description, String duration) {
        super(description);
        try {
            this.duration = LocalDate.parse(duration);
        } catch (Exception e) {
            this.duration = null;
        }
    }

    @Override
    public String toString() {
        return " [E]" + super.toString() + "\t (at " + duration.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}