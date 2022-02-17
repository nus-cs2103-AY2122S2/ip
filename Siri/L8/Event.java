import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected String eventDate;
    protected LocalDate date;
    protected LocalDateTime dateTime;

    public Event(String description, String eventDate) {
        super(description);
        this.eventDate = eventDate;
    }

    //reused from Brigette Santoso E0564307
    public Event(String description, LocalDate date) {
        super(description);
        this.eventDate = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        this.date = date;

    }

    //reused from Brigette Santoso E0564307
    public Event(String description, LocalDateTime dateTime) {
        super(description);
        this.eventDate = dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm:ss"));
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + this.getStatusIcon() + "]" + this.description + " (at: " + this.eventDate + ")";
    }

}
