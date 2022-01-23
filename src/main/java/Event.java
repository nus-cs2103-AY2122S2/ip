public class Event extends Task {

    protected String at;

    public Event(String description) {
        super(description);
    }

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        if (at != null) {
            return "[E]" + super.toString() + " (at: " + at + ")";
        } else {
            return "[E]" + super.toString();
        }
    }
}
