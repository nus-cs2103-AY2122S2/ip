package duke;

public class Event extends Task {
    protected String at;

    public Event (String description, String at) {
        super(description);
        this.at = at;
    }

    public String getAt() {
        return at;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + this.getStatusIcon() + "] " + description + " (at: " + at + ")";
    }
}
