import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate time;

    Event(String description, String time) {
        super(description);
        this.time = LocalDate.parse(time);;
    }

    private String dateToString() {
        return time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String toString() {
        return "[E]" + super.toString() + String.format(" (at: %s)", dateToString());
    }
}