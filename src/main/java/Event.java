import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate at;

    Event(String description, LocalDate at) {
        super(description, at);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
