import java.time.LocalDate;

public class Event extends Task {
    protected LocalDate at;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + this.getStatusIcon() + " " + this.description + " (at: " + Date.toString(this.at) + ")";
    }
}
