package baron.tasks;

public class Event extends Task {
    private final String at;

    public Event(String description, String at) {
        super(description);
        this.at = at.strip();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
