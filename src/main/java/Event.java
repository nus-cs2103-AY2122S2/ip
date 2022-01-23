import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDate at;

    public Event(String description, String at) {
        super(description);
        this.at = this.parseDate(at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
