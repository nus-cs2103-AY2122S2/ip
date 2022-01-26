import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate atDate;
    protected LocalTime atTime;

    public Event(String description, LocalDate atDate, LocalTime atTime) {
        super(description);
        this.atDate = atDate;
        this.atTime = atTime;
    }
    protected String getDateTime() {
        return atDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + " "
                + atTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getDateTime() + ")";
    }
}