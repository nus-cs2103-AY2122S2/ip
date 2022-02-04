import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate at;

    public Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at);
    }

    public Event(String description, LocalDate at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    String formatDate() {
        return this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
    
    @Override
    public Task mark() {
        return new Event(this.description, this.at, true);
    }
    
    @Override
    public Task unmark() {
        return new Event(this.description, this.at, false);
    }
    
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.formatDate() + ")";
    }
}
