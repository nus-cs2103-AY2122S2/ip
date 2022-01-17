public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }
    
    @Override
    public Task mark() {
        return new Event(this.description, this.at, true);
    }
    
    @Override
    public Task unmark() {
        return new Event(this.description, this.at, false);
    }
    
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
