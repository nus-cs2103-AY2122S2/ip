import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected String at;
    protected LocalDate parsedAt;

    public Event (String description, String at) {
        super(description);
        this.at = at;
        try {
            parsedAt = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
           parsedAt = null;
        }
    }

    @Override
    public String toString() {
        if (parsedAt != null) {
            String date =  parsedAt.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            return "[E]" + "[" + this.getStatusIcon() + "] " + description + " (at: " + date + ")";
        } else {
            return "[E]" + "[" + this.getStatusIcon() + "] " + description + " (at: " + at + ")";
        }
    }
}
