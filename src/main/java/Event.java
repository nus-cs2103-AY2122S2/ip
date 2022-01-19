public class Event extends Task {
    protected String duration;

    public Event(String descriptor, String duration) {
        super(descriptor);
        this.duration = duration;
    }

    @Override
    public String toString() {
        return String.format("[E] %s (at %s)", super.toString(), duration);
    }
}
