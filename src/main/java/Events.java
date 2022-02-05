import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * tasks that STARTS at a specific time and ENDS at a specific time.
 */
public class Events extends Task {

    private String at;
    public LocalDate date;

    public Events(String description, String at) {
        super(description);
        this.at = at;

        try {
            this.date = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            // shouldnt set the date if we didnt receive it in the right format
        }

    }

    @Override
    public String toString() {
        String date = (date != null ? date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) : at);
        return "[E]" + "[" + super.getStatusIcon() + "] " + super.getDescription() + " (at: " + date + ")";
    }
}
