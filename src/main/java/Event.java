import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents tasks that start at a specific time and ends at a specific time.
 */
public class Event extends Task {
    protected LocalDate time;

    public Event(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    /**
     * Returns a string representation of the object, including the task type(i.e. Event), description,
     * and time.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[E]" + super.toString() + " (at: " + time.format(formatter) + ")";
    }
}
