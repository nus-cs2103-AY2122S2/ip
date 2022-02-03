package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDate dead;
    private DateTimeFormatter dateOutputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
    private DateTimeFormatter timeOutputFormatter = DateTimeFormatter.ofPattern("HHmm");
    private LocalTime timeFrom;
    private LocalTime timeTo;

    public Event(String name, LocalDate dead, LocalTime timeFrom, LocalTime timeTo) {
        super("E", name, dead, timeFrom, timeTo);
        this.dead = dead;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dead.format(dateOutputFormatter) + " " +
                timeFrom.format(timeOutputFormatter) + "-" + timeTo.format(timeOutputFormatter) + ")";
    }
}
