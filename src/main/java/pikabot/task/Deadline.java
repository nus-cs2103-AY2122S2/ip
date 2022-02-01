package pikabot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task with a date as a deadline.
 */
public class Deadline extends Task {

    protected LocalDate byDate;

    /**
     * Constructs a Deadline.
     *
     * @param description Description of the Deadline task.
     * @param byDate Date that task has to be completed by.
     */
    public Deadline(String description, LocalDate byDate) {
        super(description);
        this.byDate = byDate;
    }

    /**
     * Converts Deadline to a formatted string.
     *
     * @return Formatted string representation of Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: "
                + this.byDate.format(DateTimeFormatter.ofPattern("d-MMM-yyyy")) + ")";
    }
}
