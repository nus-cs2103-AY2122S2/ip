public class Event extends Task {
    protected String time;

    public Event(String name, String time) {
        super(name);
        this.time = time;
    }

    @Override
    public String toString() {
        return super.toString() + "(at: " + this.time + ")";
    }

    @Override
    public String getCurrentStatus() {
        return "[E]" + super.getCurrentStatus();
    }

    @Override
    public String getType() {
        return "Event Task";
    }
}
