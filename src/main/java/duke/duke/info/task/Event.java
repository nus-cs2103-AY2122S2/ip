package duke.info.task;

public class Event extends Task {

    private final String date;

    public Event(String event, String date, boolean isComplete) {
        super(event, "event", isComplete);
        this.date = date;
    }

    @Override
    public String saveFormat() {
        return super.saveFormat() + "|" + this.date;
    }

    @Override
    public String toString() {
        System.out.println(date);
        return String.format("[E]%s (at: %s)", super.toString(), this.date);
    }
}
