package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class encapsulates a Deadline task which inherits from Task.
 */
public class Deadline extends Task {

    private LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern(" MMM dd yyyy h:mm a");
        String outputDateTime = by.format(outputFormat);
        return "[D]" + super.toString() + " (by:" + outputDateTime + ")";
    }
}
