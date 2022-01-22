import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected String icon = "E";
    protected DukeDateTime at;

    public Event(String description, DukeDateTime at) {
        super(description);
        this.at = at;
    }

    public Event(String description, Boolean isDone, DukeDateTime at) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[" + icon + "]" + super.toString() + " (at: " + at.format("d MMM yyyy") + ")";
    }

    @Override
    public String getIcon() {
        return icon;
    }

    @Override
    public String getDescription() {
        return description + " /at " + at.format("yyyy-M-d");
    }

    @Override
    public Event mark() { return new Event(description, true, at); }

    @Override
    public Event unmark() { return new Event(description, false, at); }

}
