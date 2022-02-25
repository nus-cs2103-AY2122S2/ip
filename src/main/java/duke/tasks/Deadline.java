package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates a task with deadline
 */
public class Deadline extends Task {
    /**
     * Task deadline
     */
    private LocalDate deadline;

    /**
     * Instantiates a new Deadline object
     *
     * @param rank        rank of deadline task
     * @param description the description of deadline task
     * @param deadline    the date of the deadline task
     * @throws DateTimeParseException thrown when user
     * inputs an invalid date format
     */
    public Deadline(int rank, String description, LocalDate deadline) throws DateTimeParseException {
        super(rank, description);
        this.deadline = deadline;
    }

    /**
     * String representation of Deadline object
     *
     * @return the string representation of the deadline object
     */
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");
        return "[D]" + super.toString() + " (by: " + formatter.format(this.deadline) + ")";
    }
}