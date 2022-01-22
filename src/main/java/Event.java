import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String at;
    protected LocalDate date;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, LocalDate date) {
        super(description);
        this.at = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        this.date = date;

    }

    @Override
    public String getFileFormat() {
        return "E" + super.getFileFormat() + "|" + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}