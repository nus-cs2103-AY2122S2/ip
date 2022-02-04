public class Event extends Task {
    private final String time;

    public Event(String content, String time) {
        super(content);
        this.time = time;
    }

    public Event(String content, String time, boolean isDone) {
        super(content, isDone);
        this.time = time;
    }

    @Override
    public Event mark(boolean isDone) {
        return new Event(super.getContent(), time, isDone);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}
