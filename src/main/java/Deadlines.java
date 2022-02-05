import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Tasks that need to be done before a specific time/date.
 */
public class Deadlines extends Task {

    public String by;
    LocalDate date;

    /**
     * Deadline will take in a description and a by timeline.
     */
    public Deadlines(String description, String by) {
        super(description);
        this.by = by;

        try {
            this.date = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
        }
    }

    @Override
    public String toString() {
        String date = (date != null ? date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) : by);
        return "[D]" + "[" + super.getStatusIcon() + "] " + super.getDescription() + " (by: " + date + ")";
    }

}
