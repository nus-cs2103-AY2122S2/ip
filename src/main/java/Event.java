public class Event extends Task {
    protected String dateAndTime;

    public Event(String description, String dateAndTime) {
        super(description);
        this.dateAndTime = dateAndTime;
    }

    @Override
    public String toString() {
        return "[E]" + this.getStatusIcon() + " " + super.description + "(at:" + this.dateAndTime + ")";
    }
}
