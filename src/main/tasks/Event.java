package main.tasks;

import main.enums.TaskType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDateTime eventTime;

    public Event(String description, String eventTime) {
        super(description, TaskType.Event);
        this.eventTime = LocalDateTime.parse(eventTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    public Event(String description, String eventTime, boolean isDone) {
        super(description, TaskType.Event, isDone);
        this.eventTime = LocalDateTime.parse(eventTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    public LocalDateTime getEventTime() {
        return this.eventTime;
    }

    @Override
    public String toStoreString() {
        return super.toStoreString() + "~" + this.getEventTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (at: %s)",
                this.getEventTime().format(DateTimeFormatter.ofPattern("dd/MMM/yyyy HH:mm")));
    }
}
