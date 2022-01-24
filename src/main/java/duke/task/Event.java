package duke.task;

import duke.task.Task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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

    public Event(String name, boolean isMark, LocalDate eventDate, LocalTime startTime, LocalTime endTime) {
        super(name, isMark);
        this.eventDate = eventDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d MMM yyyy");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("h:mma");

        return String.format("[E]%s (at: %s %s - %s)", super.toString(), eventDate.format(dateFormat),
                startTime.format(timeFormat), endTime.format(timeFormat));
    }

    @Override
    public String toData() {
        return "E|" + super.toData() + "|" + eventDate + "|" + startTime + "|" + endTime;
    }
}
