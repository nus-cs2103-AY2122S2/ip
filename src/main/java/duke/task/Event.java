package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends duke.task.Task {
    protected String at;

    public Event(String name, String at) {
        super(name);
        try {
            LocalDate d1 = LocalDate.parse(at);
            this.at = d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException ex) {
            this.at = at;
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (by:" + at + ")";
    }

    @Override
    public String toText() {
        return "E | " + (this.getDone() ? 1 : 0) + " | " + this.getName() + " | " + this.at + "\n";
    }
}
