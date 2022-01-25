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

    @Override
    public String saveFileFormat() {
        return EVENT_SYMBOL + "|" + isDone + "|" + taskDescription + "\n";
    }

    @Override
    public void readFile() {

    }

    @Override
    public String toString() {
        return "[" + EVENT_SYMBOL + "]" + super.toString() + " (at: " + this.eventTime + ")";
    }
}
