public class Event extends Task{
    private String eventTime;

    Event(String description, String eventTime, Boolean completed) {
        super(description, completed);
        this.eventTime = eventTime;
    }

    Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    @Override
    public String getType() {
        return "Event";
    }

    public String getTime() {
        return this.eventTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.eventTime + ")";
    }
}