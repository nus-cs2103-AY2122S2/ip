import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * tasks that STARTS at a specific time and ENDS at a specific time.
 */
public class Events extends Task {

    private String at;
    public LocalDate date;
    public LocalDateTime dateTime;

    public Events(String description, String at) {
        super(description);
        this.at = at;
    }

    public Events(String description, LocalDate date) {
        super(description);
        this.date = date;
        this.at = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    public Events(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
        this.at = dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm:ss"));
    }

    @Override
    public String toString() {
        return "[E]" + "[" + super.getStatusIcon() + "] " + super.getDescription() + " (at: " + at + ")";
    }
}
