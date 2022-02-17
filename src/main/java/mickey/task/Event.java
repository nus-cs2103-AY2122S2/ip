package mickey.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Event task.
 */
public class Event extends Task {
    /**
     * Event date.
     */
    private final LocalDateTime at;

    /**
     * Constructor.
     *
     * @param description Task description.
     * @param date Event date.
     * @throws DateTimeParseException DateTime parse error.
     */
    public Event(String description, String date) throws DateTimeParseException {
        super(description);
        this.at = LocalDateTime.parse(date, (DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
    }

    /**
     * Gets Event at date.
     *
     * @return event at date.
     */
    public LocalDateTime getAt() {
        return this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"))
                + ")";
    }
}
