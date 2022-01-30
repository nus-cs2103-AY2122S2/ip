package main.tasks;

import main.enums.TaskType;

public class Event extends Task{
    protected String eventTime;

    public Event(String description, String eventTime) {
        super(description, TaskType.Event);
        this.eventTime = eventTime;
    }

    public String getEventTime() {
        return this.eventTime;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (at: %s)", this.getEventTime());
    }
}
