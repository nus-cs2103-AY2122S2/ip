package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The type Deadline.
 */
public class Deadline extends Task {

    /**
     * The By date.
     */
    protected LocalDateTime byDateTime;

    /**
     * Instantiates a new Deadline.
     *
     * @param description the description
     * @param byDateTime          the by
     */
    public Deadline(String description, LocalDateTime byDateTime) {
        super(description);
        this.byDateTime = byDateTime;
    }



    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        return "[D]" + super.toString() + " (by: " + formatter.format(byDateTime) + ")";
    }
}
