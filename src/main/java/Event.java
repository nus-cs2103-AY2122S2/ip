public class Event extends Task{
    protected String at;
    public Event(String task, String at) {
        super(task.trim());
        this.at = at.trim();
    }

    public Event(String task, boolean done) {
        super(task, done);
    }

    @Override
    public Event mark() {
        return new Event(task, true);
    }

    @Override
    public Event unmark() {
        return new Event(task, false);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
