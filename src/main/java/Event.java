public class Event extends Task {

    protected String at;

    Event(String description, String at) {
        super(description, "E", at);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
