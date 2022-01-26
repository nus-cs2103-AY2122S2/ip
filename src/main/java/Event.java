import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate eventDate;
    private LocalTime eventTime;
    private static final char EVENT_SYMBOL = 'E';

    public Event() {
        super();

        this.eventDate = LocalDate.now();
    }

    public Event(boolean isDone, String taskDescription, LocalDate eventDate, LocalTime eventTime) {
        super(isDone, taskDescription);

        this.eventDate = eventDate;
        this.eventTime = eventTime;
    }

    @Override
    public String saveFileFormat() {
        return EVENT_SYMBOL + "|" + this.isDone + "|" + taskDescription + "|"
                + this.eventDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + "|"
                + this.eventTime.format(DateTimeFormatter.ofPattern("hh:mm a"))
                + "\n";
    }

    @Override
    public String toString() {
        return "[" + EVENT_SYMBOL + "]" + super.toString() + " (at: "
                + this.eventDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " "
                + this.eventTime.format(DateTimeFormatter.ofPattern("hh:mm a"))
                + ")";
    }
}
