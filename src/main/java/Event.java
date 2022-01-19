public class Event extends Task {
    private final String timeFrame;

    public Event(String description, String timeFrame) {
        super(description);
        this.timeFrame = timeFrame;
    }

    public String getEvent() {
        return "[E]" + this.getTask() + "(at: " + timeFrame + ")\n";
    }
}