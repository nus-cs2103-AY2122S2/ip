public class Event extends Task {
    private String dateTime;

    public Event(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)",
                super.getStatusIcon(), super.getDescription(), this.dateTime);
    }
}
