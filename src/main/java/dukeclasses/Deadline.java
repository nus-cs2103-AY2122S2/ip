package dukeclasses;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a due date.
 */
public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * Constructor for Deadline.
     * @param description Describes the deadline.
     * @param deadline LocalDate that state the due date of instance of Deadline.
     */
    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Returns a string of the identity of the Deadline(i.e. its description and due Date).
     * Identity used in taskList.
     *
     * @return String to identify the Deadline.
     */
    @Override
    public String identify() {
        String dateString = deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy" ));
        if (super.getIsDone()) {
            return String.format("[D][X] %s (by: %s)\n", super.getDescription(), dateString);
        } else {
            return String.format("[D][ ] %s (by: %s)\n", super.getDescription(), dateString);
        }
    }
}
