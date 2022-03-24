package duke;

public class Event extends Task {
    private final String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String isDone, String description, String at) {
        super(isDone, description);
        this.at = at;
    }

    @Override
    public String toFileFormat() {
        return String.format("E | %s | %s\n", super.toFileFormat(), at);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), at);
    }
}
