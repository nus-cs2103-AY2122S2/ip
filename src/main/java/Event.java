public class Event extends Task{
    private String eventAt;

    public Event(String title, String eventAt) {
        super(title);
        this.eventAt = eventAt;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.eventAt + ")";
    }
}
