public class Event extends Task {
    // attribute
    protected String type;

    // constructor
    public Event(String description, String at) {
        super(description, at);
        this.type = "E";
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getBy() {
        return super.by;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + super.by + ")";
    }
}
