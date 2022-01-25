public class Event extends Task {
    private final String dateTime;

    String getDateTime() {
        return dateTime;
    }

    Event(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + getDateTime() + ")";
    }
}
