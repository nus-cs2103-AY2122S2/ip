package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDate time;

    public Deadline(String name, String time) throws DateTimeParseException {
        this(name, time, false);
    }

    public Deadline(String name, String time, boolean isDone) throws DateTimeParseException {
        super(name, isDone);
        this.time = LocalDate.parse(time);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                time.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    @Override
    public String convertToFileFormat() {
        if (isDone) {
            return "D | 1 | " + name + " | " + time.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
        return "D | 0 | " + name + " | " + time.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
}