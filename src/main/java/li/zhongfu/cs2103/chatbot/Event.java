package li.zhongfu.cs2103.chatbot;

public class Event extends Task {
    private String eventTime;

    public Event(String name, String eventTime) {
        super(name);
        this.eventTime = eventTime;
    }

    public String getEventTime() {
        return this.eventTime;
    }

    public String toString() {
        return String.format("[E][%s] %s (at: %s)", this.getDone() ? "X" : " ", this.getName(), this.getEventTime());
    }
}
