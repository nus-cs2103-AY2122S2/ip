package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates a task that has a deadline.
 */
public class Deadline extends ScheduledTask {

    /**
     * Initialises a Deadline task with a specified
     * description and deadline.
     *
     * @param description the description of this Deadline task.
     * @param deadline the deadline of this Deadline task as a date.
     */
    public Deadline(String description, LocalDate deadline) {
        super(description, deadline);
    }

    /**
     * Returns the string representation of this Deadline task.
     *
     * @return the string representation of this Deadline task.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[D]")
                .append(super.toString())
                .append(" (by: ")
                .append(getDate().format(DateTimeFormatter.ofPattern("dd MMM yyyy")))
                .append(")");
        return sb.toString();
    }
}
