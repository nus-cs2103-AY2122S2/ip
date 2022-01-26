public class Event extends Task{

    private String eventDate;

    public Event(String description, String eventDate, boolean isDone) {
        super(description, isDone);
        this.eventDate = eventDate;
    }

    @Override
    public String getTaskString() {
        return "EVENT" + "," + super.isDone + "," + super.description + "," + this.eventDate;
    }

    public String getEventDate() {
        return eventDate;
    }

    @Override
    public String toString() {
        return "[E] [" + this.getStatusIcon() + "] " + super.getDescription() + " (" + this.getEventDate() + ")";
    }
}
