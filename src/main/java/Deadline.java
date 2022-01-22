import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected String icon = "D";
    protected DukeDateTime by;

    public Deadline(String description, DukeDateTime by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, Boolean isDone, DukeDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[" + icon + "]" + super.toString() + " (by: " + by.format("d MMM yyyy") + ")";
    }

    @Override
    public String getIcon() {
        return icon;
    }

    @Override
    public String getDescription() {
        return description + " /by " + by.format("yyyy-M-d");
    }

    @Override
    public Deadline mark() { return new Deadline(description, true, by); }

    @Override
    public Deadline unmark() { return new Deadline(description, false, by); }

}
