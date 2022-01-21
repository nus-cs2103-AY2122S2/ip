public class Event extends Task {
    private final String time;

    public Event(String taskName, String time) {
        super(taskName);
        this.time = time;
    }

    public Event(String taskName, boolean isDone, String time) {
        super(taskName, isDone);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (at: %s)", this.time);
    }

    @Override
    public String encode() {
        return "E <> " + super.encode() + " <> " + time + "\n";
    }
}
