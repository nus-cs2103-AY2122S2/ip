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

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }

    public String getType() {
        return  "E";
    }

    @Override
    public String formatToSave() {
        return super.formatToSave() + "|" + this.at;
    };

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getDateTime() + ")";
    }
}