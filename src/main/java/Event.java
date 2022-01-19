public class Event extends Task {
    protected String by;

    public Event(String desc, String by) {
        super(desc, "E");
        this.by = "(at: " + by + ")";
    }
    public Event(String desc, String by, boolean done) {
        super(desc, done, "E");
        this.by = by;
    }

    @Override
    public String getBy() {
        return this.by;
    }
    @Override
    public Task mark() {
        return new Event(this.desc, this.by, true);
    }
    @Override
    public Task unmark() {
        return new Event(this.desc, this.by, false);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (by: " + by + ")";
    }
}
