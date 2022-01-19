public class Event extends Task {
    private final String time;

    public Event(String content, String time) {
        super(content);
        this.time = time;
    }

    public Event(String content, String time, boolean done) {
        super(content, done);
        this.time = time;
    }

    @Override
    public Event mark(boolean done) {
        return new Event(super.getContent(), time, done);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}
