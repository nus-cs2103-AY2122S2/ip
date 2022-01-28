package duke.info.task;

public class Event extends Task {

    public Event(String event, String date, boolean isComplete) {
        super(event, "event", isComplete, date);
    }

    @Override
    public String saveFormat() {
        return super.saveFormat() + "|" + this.getDateString();
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.getDateString());
    }
}
