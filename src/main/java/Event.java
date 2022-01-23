public class Event extends Task {

    protected String dateTime;

    public Event(String name, String dateTime) {
        super(name);
        this.dateTime = dateTime;
    }

    public Event(String name, boolean isMark, String dateTime) {
        super(name, isMark);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), dateTime);
    }

    @Override
    public String toData() {
        return "E|" + super.toData() + "|" + dateTime;
    }
}
