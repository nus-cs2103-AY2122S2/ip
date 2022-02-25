import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDate date;

    public Event(int rank, String description, LocalDate date) throws DateTimeParseException {
        super(rank, description);
        this.date = date;
    }

    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");
        return "[E]" + super.toString() + " (at: " + formatter.format(this.date) + ")";
    }
}
