package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task. A Deadline object corresponds to a String description of the Task
 * and the LocalDateTime the task is due.
 */
public class Deadline extends Task {

    private LocalDateTime by;

    /**
     * Constructs a deadline task.
     * @param description Description of deadline task.
     * @param by LocalDateTime of the deadline task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a String representation of the deadline task in the desired format.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern(" MMM dd yyyy h:mm a");
        String outputDateTime = by.format(outputFormat);
        return "[D]" + super.toString() + " (by:" + outputDateTime + ")";
    }
}
