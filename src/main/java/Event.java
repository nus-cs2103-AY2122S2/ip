public class Event extends Task {
    private String eventTime;

    public Event() {
        super();

        this.eventTime = "";
    }

    public Event(String taskDescription, String eventTime) {
        super(taskDescription);

        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "\n (at: " + this.eventTime + ")";
    }
}
