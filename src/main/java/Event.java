public class Event extends Task{

    private String eventDate;

    public Event(String description, String eventDate) {
        super(description);
        this.eventDate = eventDate;
    }

    public String getEventDate() {
        return eventDate;
    }

    @Override
    public String toString() {
        return "[E] [" + this.getStatusIcon() + "] " + super.getDescription() + " (" + this.getEventDate() + ")";
    }
}
