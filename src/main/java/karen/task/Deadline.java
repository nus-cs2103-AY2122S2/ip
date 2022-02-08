package karen.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Stores the (/by) date in which the Deadline occurs by
 */
public class Deadline extends Task {
    protected LocalDate byDate;

    /**
     * Constructor for Deadline
     *
     * @param description Description of what Deadline is for
     * @param by A "yyyy-mm-dd" string for the Deadline to be completed by
     */
    public Deadline(String description, String by) {
        super(description);
        byDate = parseDate(by);
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public String toSaveData() {
        return String.format("D|%s|%s|%s", isDone, getDescription(), byDate);
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byDate.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
