import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDateTime dueDate;

    public Event(String description, String dueDate) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime parsedDate = LocalDateTime.parse(dueDate, formatter);
        this.dueDate = parsedDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (at: " + dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy hhmma")) +")";
    }
}
