package tasks;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    private Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    public String getAt() {
        return this.at;
    }

    @Override
    public Event clone() {
        return new Event(super.description, this.at, super.isDone);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
