package li.zhongfu.cs2103.chatbot;

public class Deadline extends Task {
    private String eventTime;

    public Deadline(String name, String eventTime) {
        super(name);
        this.eventTime = eventTime;
    }

    public String getDeadline() {
        return this.eventTime;
    }

    public String toString() {
        return String.format("[D][%s] %s (by: %s)", this.getDone() ? "X" : " ", this.getName(), this.getDeadline());
    }
}
