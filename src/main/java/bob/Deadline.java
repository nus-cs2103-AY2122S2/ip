package bob;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends bob.Task {
    protected LocalDateTime by;

    /**
     * Constructor for a Deadline object, given description and a deadline in parseable format.
     *
     * @param description Description of the Deadline instance.
     * @param by          A string representing deadline in parseable format.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by);
    }

    /**
     * Constructor for a Deadline object, given description, deadline and status.
     *
     * @param description Description of the Deadline instance.
     * @param by          A string representing deadline in parseable format.
     * @param isDone      Status of the Deadline instance.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = LocalDateTime.parse(by);
    }

    /**
     * Generates a string representing this Task object for saving.
     *
     * @return A string representing this Task for saving.
     */
    @Override
    public String generateSavedEntry() {
        return String.format("D|%s|%s", isDone ? "1" : "0", description + "/by "
                + by.format(DateTimeFormatter.ISO_DATE_TIME));
    }

    /**
     * Returns a string representation of this Task for display.
     *
     * @return A string representing this task for display.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                by.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
