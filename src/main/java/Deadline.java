import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDate by;
    protected LocalTime time;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            String[] split = by.split(" ");
            this.by = LocalDate.parse(split[0]);
            if (split.length > 1) {
                time = LocalTime.parse(split[1]);
            } else {
                time = null;
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date");
        }
    }

    public Deadline(String description, boolean isDone, String by) throws DukeException {
        super(description, isDone);
        try {
            String[] split = by.split(" ");
            this.by = LocalDate.parse(split[0]);
            if (split.length > 1) {
                time = LocalTime.parse(split[1]);
            } else {
                time = null;
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date");
        }
    }

    public String getBy() {
        return by.toString();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + (time != null ? (" " + time.format(DateTimeFormatter.ofPattern("HH:mm"))) : "") + ")";
    }

    @Override
    public String getFileString() {
        return "D|" + (isDone == true ? "1|" : "0|") + getDescription() + "|" + getBy()
                + (time != null ? (" " + time.toString()) : "") + "\n";
    }
}
