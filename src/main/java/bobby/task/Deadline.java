package bobby.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents the Deadline object.
 */
public class Deadline extends Task {
    private final LocalDate date;

    /**
     * Constructor for Deadline Task.
     *
     * @param taskName The name of the task.
     * @param byDate The date tied to the Deadline task.
     */
    public Deadline(String taskName, String byDate) {
        super(taskName);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        date = LocalDate.parse(LocalDate.parse(byDate, formatter).format(formatter2));
        super.setDate(date);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
