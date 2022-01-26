public class Event extends Task{
    protected String at;

    public Event(String name, String at) {
        super(name);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (by:" + at + ")";
    }

    @Override
    public String toText() {
        return "E | " + (this.getDone() ? 1 : 0) + " | " + this.getName() + " | " + this.at + "\n";
    }
}
