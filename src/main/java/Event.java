public class Event extends Task {
    private final String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    public String getDescription() {
        return "[E]" + this.getDescription() + "(at: " + time + ")\n";
    }
}
