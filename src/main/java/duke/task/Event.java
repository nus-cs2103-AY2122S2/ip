package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDate time;

    public Event(String name, String time) throws DateTimeParseException {
        this(name, time, false);
    }

    public Event(String name, String time, boolean isDone) throws DateTimeParseException {
        super(name, isDone);
        this.time = LocalDate.parse(time);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +
                time.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    @Override
    public String convertToFileFormat() {
        if (isDone) {
            return "E | 1 | " + name + " | " + time.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
        return "E | 0 | " + name + " | " + time.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
}