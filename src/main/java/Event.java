import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime at;

    public Event(String description, String at) {
        super(description, false);
        String[] dateTime = at.split(" ");
        this.at = LocalDateTime.of(LocalDate.parse(dateTime[0]), LocalTime.parse(dateTime[1], DateTimeFormatter.ofPattern("HHmm")));
    }

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = LocalDateTime.parse(at);
    }

    public String formatData() {
        return "E|" + super.formatData() + "|" + at.toString();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma")) + ")";
    }
}
