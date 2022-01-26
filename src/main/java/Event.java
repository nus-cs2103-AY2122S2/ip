public class Event extends Task {
    protected String at;

    Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toMemory() {
        return "E" + super.toMemory() + "@" + this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
