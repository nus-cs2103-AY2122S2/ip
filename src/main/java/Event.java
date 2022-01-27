public class Event extends Task {
    protected DateTime dateTime;

    public Event(String description, String time) throws UltoiException {
        super(description);
        try {
            this.dateTime = new DateTime(time);
        } catch (UltoiException e) {
            throw e;
        }
    }

    public String toInputString() {
        return "event" + description + " /at " + dateTime.toInputString();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.dateTime.toString() + ")";
    }
}
