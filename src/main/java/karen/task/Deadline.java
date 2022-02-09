package karen.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Stores the (/by) date in which the Deadline occurs by
 */
public class Deadline extends Task {
    protected LocalDateTime byDate;

    /**
     * Constructor for Deadline
     *
     * @param description Description of what Deadline is for
     * @param by A "yyyy-mm-dd" string for the Deadline to be completed by
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        byDate = by;
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public String toSaveData() {
        return String.format("D|%s|%s|%s", isDone, getDescription(), formatDate(byDate));
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
        return "[D]" + super.toString() + " (by: " + byDate.format(formatter) + ")";
    }
}
