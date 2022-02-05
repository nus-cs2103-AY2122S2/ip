package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.tag.Tag;

/**
 * The type Deadline.
 */
public class Deadline extends Task {

    /**
     * The By date.
     */
    protected LocalDateTime byDateTime;

    /**
     * Instantiates a new Deadline with tag.
     *
     * @param description the description
     * @param byDateTime  the by date time
     * @param tag         the tag
     */
    public Deadline(String description, LocalDateTime byDateTime, Tag tag) {
        super(description, tag);
        this.byDateTime = byDateTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        return "[D]" + super.toString() + " (by: " + formatter.format(byDateTime) + ")";
    }
}
