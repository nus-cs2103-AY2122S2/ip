import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Tasks that need to be done before a specific time/date.
 */
public class Deadlines extends Task {

    String by;
    LocalDate date;
    LocalDateTime dateTime;

    /**
     * Deadline will take in a description and a by timeline.
     */
    public Deadlines(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Overloaded constructor that takes in a description and a date.
     */
    public Deadlines(String description, LocalDate date) {
        super(description);
        this.date = date;
        this.by = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     * Overloaded constructor that takes in a description and a dateTime.
     */
    public Deadlines(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
        this.by = dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm:ss"));
    }

    @Override
    public String toString() {
        return "[D]" + "[" + super.getStatusIcon() + "] " + super.getDescription() + " (by: " + by + ")";
    }

}
