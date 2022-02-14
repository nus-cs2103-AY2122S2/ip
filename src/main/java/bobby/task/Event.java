package bobby.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents the Event object.
 */
public class Event extends Task {
    private final LocalDate date;

    /**
     * Constructor class for Event task.
     * @param taskName The name of the task.
     * @param atDate The date tied to this Event task.
     */
    public Event(String taskName, String atDate) {
        super(taskName);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        date = LocalDate.parse(LocalDate.parse(atDate, formatter).format(formatter2));
        super.setDate(date);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
