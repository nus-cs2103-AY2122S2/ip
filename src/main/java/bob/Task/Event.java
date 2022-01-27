package bob.Task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDate date;
    private final LocalTime startTime;
    private final LocalTime endTime;
    public Event(String name, LocalDate date, LocalTime startTime, LocalTime endTime) {
        super(name);
        super.setStatus(0);
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        super.type = "E";
    }

    @Override
    public String printStatus() {
        return "[E] " + Task.statusSymbols[super.getStatus()] + " " + this + " (at: "
                + this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                + " from " + this.startTime.format(DateTimeFormatter.ofPattern("HH:mm")) + " to "
                + this.endTime.format(DateTimeFormatter.ofPattern("HH:mm")) + ")";
    }

    @Override
    public String toString() {
        return super.getName();
    }
}
