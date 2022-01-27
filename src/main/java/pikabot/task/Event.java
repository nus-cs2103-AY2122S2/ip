package pikabot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate dateAt;

    public Event(String description, LocalDate dateAt) {
        super(description);
        this.dateAt = dateAt;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " +
            this.dateAt.format(DateTimeFormatter.ofPattern("d-MMM-yyyy")) + ")";
    }

}
