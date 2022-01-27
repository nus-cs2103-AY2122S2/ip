package chatbot.task;

import java.time.LocalDate;
import java.time.LocalTime;

public class Event extends Task {
    private final LocalDate startDate;
    private final LocalTime startTime;
    private final LocalDate endDate;
    private final LocalTime endTime;

    public Event(String desc, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        super(desc);
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
    }

    @Override public String toString() {
        return "[E]" + super.toString() + " (at: " + startDate.toString() + " " + startTime.toString() + " to "
                + endDate.toString() + " " + endTime.toString() + ")";
    }
}