import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDate at;

    public Event(String description, String at) throws DukeException {
        super(description);
        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date");
        }
    }

    public Event(String description, boolean isDone, String at) throws DukeException {
        super(description, isDone);
        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date");
        }
    }

    public String getAt() {
        return at.toString();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String getFileString() {
        return "D|" + (isDone == true ? "1|" : "0|") + getDescription() + "|" + getAt() + "\n";
    }
}
