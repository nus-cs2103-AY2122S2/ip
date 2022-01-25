public class Event extends Task {
    private String eventTime;
    private static final char EVENT_SYMBOL = 'E';

    public Event() {
        super();

        this.eventTime = "";
    }

    public Event(String taskDescription, String eventTime) {
        super(taskDescription);

        this.eventTime = eventTime;
    }

    public Event(boolean isDone, String taskDescription, String eventTime) {
        super(isDone, taskDescription);

        this.eventTime = eventTime;
    }

    @Override
    public String saveFileFormat() {
        return EVENT_SYMBOL + "|" + this.isDone + "|" + taskDescription + "|" + this.eventTime + "\n";
    }

    @Override
    public String toString() {
        return "[" + EVENT_SYMBOL + "]" + super.toString() + " (at: " + this.eventTime + ")";
    }
}
