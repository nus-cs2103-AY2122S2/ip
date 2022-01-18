public class Event extends Task {

    public Event(String description, String on) {
        super(description, on);
    }

    @Override
    public String toString() {
        return "[E][" + super.getStatusIcon() + "] " + this.description + " (at: " + this.date + ")";
    }
}
