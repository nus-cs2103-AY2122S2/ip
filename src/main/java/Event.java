public class Event extends Task {

    protected String at;

    Event(String description, String at) {
        super(description);
        this.at = at.substring(3);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
