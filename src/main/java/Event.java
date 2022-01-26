import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Event  extends Task {
    private LocalDate time;

    public Event(String content, String time) {
        super(content);
        this.time = LocalDate.parse(time, DateTimeFormatter.ISO_DATE);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)) + ")" ;
    }
}
