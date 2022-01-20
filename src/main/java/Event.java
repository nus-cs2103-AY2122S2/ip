public class Event extends Task {
    private String at;
    public Event(String content, String at) {
        super(content);
        this.at = at;
    }

    @Override
    public String toString() {
        if (getIsDone()) {
            return "[E][X] " + getContent() + " (at: " + at + ")";
        } else {
            return "[E][ ] " + getContent() + " (at: " + at + ")";
        }
    }
}
