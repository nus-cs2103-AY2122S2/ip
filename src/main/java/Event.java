public class Event extends Task {
    protected String at;

    public Event(String[] details) {
        this.description = details[0];
        this.at = details[1];
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
