package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class EventTask extends Task{
    private final LocalDateTime eventTime;

    public EventTask(String task, String eventTime) throws DateTimeParseException {
        super(task);
        this.eventTime = LocalDateTime.parse(eventTime);
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + eventTime + ")";
    }
}
