import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{
    private final LocalDateTime eventAt;
    private static final String DEFAULT_DATE_FORMAT = "dd MMMM yyyy HHmm";
    private static final String SAVE_FILE_DATE_FORMAT = "d/MM/yyyy HHmm";

    public Event(String title, LocalDateTime eventAt) {
        super(title);
        this.eventAt = eventAt;
    }

    @Override
    public String getSaveFormat() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(SAVE_FILE_DATE_FORMAT);
        return String.format("%s | %s", super.getSaveFormat(), this.eventAt.format(format));
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);
        return String.format("[E]%s (at: %s)", super.toString(), this.eventAt.format(format));
    }
}
