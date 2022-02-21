package main.duke.tasks;

import main.duke.enums.TaskType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDateTime eventTime;

    public Event(String description, LocalDateTime eventTime) {
        super(description, TaskType.Event);
        this.eventTime = eventTime;
    }

    public Event(String description, LocalDateTime eventTime, boolean isDone) {
        super(description, TaskType.Event, isDone);
        this.eventTime = eventTime;
    }

    public LocalDateTime getEventTime() {
        return this.eventTime;
    }

    public Task clone() {
        return new Event(this.getDescription(), this.getEventTime(), this.getIsDone());
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
