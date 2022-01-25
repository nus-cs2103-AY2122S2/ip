import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Event extends Task {
    public Event(String content, LocalDateTime date) {
        super(content, date);
    }

    @Override
    public String toString() {
        if (getIsDone()) {
            return "[E][X] " + getContent() + " (by: " + date
                    .format(Printer.formatter) + ")";
        } else {
            return "[E][ ] " + getContent() + " (by: " + date
                    .format(Printer.formatter) + ")";
        }
    }
}
