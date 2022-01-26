import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Event extends Task {

    protected LocalDateTime ldt;

    public Event(String description, String at) {
        super(description);
        this.ldt = parseString(at);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT);
        return "[E]" + super.toString() + " (at: " + ldt.format(formatter) + ")";
    }

    private LocalDateTime parseString(String at) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(at, formatter);

    }
}