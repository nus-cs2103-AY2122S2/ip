import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Deadline extends Task {
    public Deadline(String content, LocalDateTime date) {
        super(content, date);
    }

    @Override
    public String toString() {
        if (getIsDone()) {
            return "[D][X] " + getContent() + " (by: " + date
                    .format(Printer.formatter) + ")";
        } else {
            return "[D][ ] " + getContent() + " (by: " + date
                    .format(Printer.formatter) + ")";
        }
    }
}
