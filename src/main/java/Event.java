public class Event extends Task {

    protected String icon = "E";
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, Boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[" + icon + "]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String getIcon() {
        return icon;
    }

    @Override
    public String getDescription() {
        return description + " /at " + at;
    }

    @Override
    public Event mark() { return new Event(description, true, at); }

    @Override
    public Event unmark() { return new Event(description, false, at); }

}
