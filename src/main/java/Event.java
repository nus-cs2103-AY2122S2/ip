import java.time.LocalDate;

public class Event extends Task {
    public Event(String description, LocalDate at) {
        super(description, at);
    }

    @Override
    public String toString() {
        return "[E]" + this.getStatusIcon() + " " + this.description + " (at: " + Date.toString(this.date) + ")";
    }
}
