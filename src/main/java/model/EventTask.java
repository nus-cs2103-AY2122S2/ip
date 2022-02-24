package model;

public class EventTask extends Task{
    private final String eventTime;

    public EventTask(String task, String eventTime) {
        super(task);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + eventTime + ")";
    }
}
