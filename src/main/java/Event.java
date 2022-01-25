import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate eventTime;
    private LocalTime time;

    public Event() {
        super();

        this.eventTime = LocalDate.now();
    }

    public Event(String taskDescription, LocalDate eventTime) {
        super(taskDescription);

        this.eventTime = eventTime;
    }

    public Event(String taskDescription, LocalDate eventTime, LocalTime time) {
        super(taskDescription);

        this.eventTime = eventTime;
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.eventTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " "
                + this.time.format(DateTimeFormatter.ofPattern("hh:mm a"))
                + ")";
    }
}
