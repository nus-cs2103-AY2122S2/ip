import java.time.LocalDate;
import java.time.LocalTime;

public class Event extends Task {

    protected LocalDate eventDate;
    protected LocalTime startTime;
    protected LocalTime endTime;

    public Event(String name, LocalDate eventDate, LocalTime startTime, LocalTime endTime) {
        super(name);
        this.eventDate = eventDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Event(String name, boolean isMark, String dateTime) {
        super(name, isMark);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s %s-%s)", super.toString(), eventDate, startTime, endTime);
    }

    @Override
    public String toData() {
        return "E|" + super.toData() + "|" + dateTime;
    }
}
