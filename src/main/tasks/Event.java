package main.tasks;

import main.enums.TaskType;

public class Event extends Task{
    protected String eventTime;

    public Event(String description, String eventTime) {
        super(description, TaskType.Event);
        this.eventTime = eventTime;
    }

    public Event(String description, String eventTime, boolean isDone) {
        super(description, TaskType.Event, isDone);
        this.eventTime = eventTime;
    }

    public String getEventTime() {
        return this.eventTime;
    }

    @Override
    public String toStoreString() {
        return super.toStoreString() + "~" + this.getEventTime();
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (at: %s)", this.getEventTime());
    }
}
