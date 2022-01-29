package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class.
 *
 * @author Jet Tan
 */
public class Deadline extends Task {
    private final LocalDateTime by;

    /**
     * Constructor for a new instance of Deadline, containing the description and deadline of the task.
     *
     * @param description The description of the  Task.
     * @param by The desired deadline
     */
    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.by = LocalDateTime.parse(by, format);
    }

    /**
     * Returns a string containing task type, done status and description along with deadline.
     *
     * @return string containing task type, done status and description along with deadline
     */
    @Override
    public String toString() {
        DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("MMM-dd-yyyy HHmm");
        return "[D]" + super.toString() + " (by: " + by.format(displayFormat) + ")";
    }
}