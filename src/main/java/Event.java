public class Event extends Task {

    public String eventTime;

    public Event(String taskName, String eventTime) {
        super(taskName);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)",
                this.done ? "X" : " ", this.taskName, this.eventTime);
    }
}