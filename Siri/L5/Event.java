public class Event extends Task {

    String eventDate;

    public Event(String description, String eventDate) {
        super(description);
        this.eventDate = eventDate;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + this.getStatusIcon() + "]" + this.description + "(at:" + this.eventDate + ")";
    }

}
