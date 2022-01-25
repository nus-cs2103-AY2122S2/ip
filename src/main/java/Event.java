import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate eventTime;

    public Event() {
        super();

        this.eventTime = LocalDate.now();
    }

    public Event(String taskDescription, LocalDate eventTime) {
        super(taskDescription);

        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.eventTime.format(
                DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
