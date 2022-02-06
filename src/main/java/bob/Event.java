package bob;

public class Event extends bob.Task {
    protected String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    public Event(String description, String time, boolean isDone) {
        super(description, isDone);
        this.time = time;
    }

    @Override
    public String generateSavedEntry() {
        return String.format("E|%s|%s", isDone ? "1" : "0", description + "/at" + time);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.time);
    }
}
