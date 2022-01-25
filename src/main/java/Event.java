import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Event extends Task {
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    public Event(String name, LocalDate date, LocalTime startTime, LocalTime endTime) {
        super(name);
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String printStatus() {
        return "[E] " + Task.statusSymbols[super.getStatus()] + " " + this.toString() + " (at: "
                + this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                + " from " + this.startTime.format(DateTimeFormatter.ofPattern("HH:mm")) + " to "
                + this.endTime.format(DateTimeFormatter.ofPattern("HH:mm")) + ")";
    }

    @Override
    public String toString() {
        return super.getName();
    }
}
