public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description, false);
        this.at = at;
    }

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }

    public String formatData() {
        return "E|" + super.formatData() + "|" + at.toString();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
