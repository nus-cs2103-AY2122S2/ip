public class Event extends Task {
    private String time;

    public Event(String description, String time) {
        super(description, "E");
        this.time = time;
    }

    @Override
    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}
