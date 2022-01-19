public class Event extends Task {

    protected String at;
    protected String type;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.type = "event";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}