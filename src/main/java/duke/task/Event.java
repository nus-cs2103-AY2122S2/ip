package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Task type: Event
 */

public class Event extends Task {
    public LocalDate date;
    public String stringTime;

    public Event(String input) {
        super(input.substring(6, input.indexOf("/")));
        this.stringTime = input.substring(input.indexOf("/") + 4).trim();
        this.date = LocalDate.parse(stringTime, DateTimeFormatter.ofPattern("d/M/yyyy"));
    }

    public String getFullDateTime() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + ("(at: " + this.getFullDateTime() + ")");
    }
}
