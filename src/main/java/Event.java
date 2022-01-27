public class Event extends Task{
    private String eventDate;

    public Event(String description, String eventDate) {
        super(description);
        this.eventDate = eventDate;
    }

    @Override
    public String getSaveString() {
        return super.getSaveString() + "|" + eventDate;
    }

    @Override
    public String getIcon() {
        return "E";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (at: %s)", this.eventDate);
    }
}
