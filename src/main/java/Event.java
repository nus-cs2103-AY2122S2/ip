import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{
    private final LocalDateTime eventAt;
    private static final String DEFAULT_DATE_FORMAT = "d/MM/yyyy HHmm";
    private static final String OUTPUT_DATE_FORMAT = "dd MMMM yyyy HHmm";

    public Event(String title, String eventAt) throws DukeException {
        super(title);
        this.eventAt = parseDateTime(eventAt);
    }

    public LocalDateTime parseDateTime(String eventAt) throws DukeException {
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);
            return LocalDateTime.parse(eventAt, format);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date time format.");
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT);
        return String.format("[E] %s (at: %s)",super.toString(), this.eventAt.format(format));
    }
}
