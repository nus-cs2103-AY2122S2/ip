public class Event extends Task {
    public String atTime;

    public Event(String description, String atTime) {
        super(description);
        this.atTime = atTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.atTime + ")";
    }
}
