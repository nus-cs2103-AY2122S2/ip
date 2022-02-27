public class Event extends Task {
    private final String at;

    Event(String description, String at) {
        super(description);
        this.at = at;
    }

    Event(String isDone, String description, String at) {
        super(isDone, description);
        this.at = at;
    }

    @Override
    public String fileFormat() {
        return String.format("E | %s | %s\n", super.fileFormat(), at);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), at);
    }
}