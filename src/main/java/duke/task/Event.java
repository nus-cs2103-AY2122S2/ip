package duke.task;

import duke.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDate at;
    protected LocalTime time;

    public Event(String description, String at) throws DukeException {
        super(description);
        try {
            String[] split = at.split(" ");
            this.at = LocalDate.parse(split[0]);
            if (split.length > 1) {
                time = LocalTime.parse(split[1]);
            } else {
                time = null;
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date/time");
        }
    }

    public Event(String description, boolean isDone, String at) throws DukeException {
        super(description, isDone);
        try {
            String[] split = at.split(" ");
            this.at = LocalDate.parse(split[0]);
            if (split.length > 1) {
                time = LocalTime.parse(split[1]);
            } else {
                time = null;
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date/time");
        }
    }

    public String getAt() {
        return at.toString();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + (
                time != null ? (" " + time.format(DateTimeFormatter.ofPattern("HH:mm"))) : "") + ")";
    }

    @Override
    public String getFileString() {
        return "E|" + (isDone == true ? "1|" : "0|") + getDescription() + "|" + getAt() + (
                time != null ? (" " + time) : "") + "\n";
    }
}
