public class Event extends Task {
    protected String time;

    public Event(String name, String time) {
        this(name, time, false);
    }

    public Event(String name, String time, boolean isDone) {
        super(name, isDone);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }

    @Override
    protected String convertToFileFormat() {
        if (isDone) {
            return "E | 1 | " + name + " | " + time;
        }
        return "E | 0 | " + name + " | " + time;
    }
}
