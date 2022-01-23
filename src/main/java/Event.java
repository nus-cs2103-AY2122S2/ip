import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    public String duration;
    public LocalDate date;
    public String time;

    public Event(String description, String duration) {
        super(description);
        this.duration = duration.trim();
        String[] datetime = duration.trim().split(" ");
        this.date = LocalDate.parse(datetime[0].trim());
        this.time = datetime[1].trim();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " " + this.time + ")";
    }
}
