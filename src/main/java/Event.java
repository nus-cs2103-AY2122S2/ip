import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate date;

    public Event(String description, LocalDate date) {
        super(description, 'E');
        this.date = date;
    }

    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)",
                super.getStatusIcon(),
                super.getDescription(),
                this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
