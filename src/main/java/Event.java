public class Event extends Task{
    protected String by;

    public Event(String name, String by) {
        super(name);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (by:" + by + ")";
    }
}
