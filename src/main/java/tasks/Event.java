package tasks;

public class Event extends Task {

    protected String eventBy;

    public Event(String description, String eventBy) {
        super(description);
        this.eventBy = eventBy;
    }

    public String getEventBy() {
        return eventBy;
    }

    public void setEventBy(String eventBy) {
        this.eventBy = eventBy;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + eventBy + ")";
    }
}
