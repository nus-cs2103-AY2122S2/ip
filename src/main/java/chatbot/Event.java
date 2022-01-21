package chatbot;

public class Event extends Task {

    protected String at;

    public Event(String taskName, String at) {
        super(taskName);
        this.at = at;
    }

    public Event(String taskName, String at, boolean isDone) {
        super(taskName, isDone);
        this.at = at;
    }

    public String getDateTime() {
        return this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}