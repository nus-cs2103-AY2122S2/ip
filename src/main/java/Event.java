public class Event extends Task {
    private final String timeFrame;

    public Event(String description, String timeFrame) {
        super(description);
        this.timeFrame = timeFrame;
    }

    public Event(int mark, String description, String timeFrame) {
        super(description, mark);
        this.timeFrame = timeFrame;
    }

    public String getEvent() {
        return "[E]" + this.getTask() + "(at: " + timeFrame + ")\n";
    }

    public String getFormattedText() {
        return "E:" + this.getMark() + ":" + this.getDescription() + ":" + this.timeFrame;
    }
}