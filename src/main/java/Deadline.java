import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represent tasks that need to be done before a specific date/time.
 */
public class Deadline extends Task {
    protected LocalDate time;

    public Deadline(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    /**
     * Returns a string representation of the object, including the task type(i.e. Deadline), description,
     * and time.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("LLL dd uuuu");
        return "[D]" + super.toString() + " (by: " + time.format(formatter) + ")";
    }
}
