package aeromon.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class handles the Deadline Task type.
 */
public class Deadline extends Task {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private final LocalDate by;

    private static final String DEADLINE_STRING_FORMAT = "[D]%1$s (by: %2$s)";
    private static final String DEADLINE_OUTPUT_FORMAT = "D / %1$s / %2$s";

    /**
     * Constructs the Deadline object.
     * @param description the task description.
     * @param by the deadline which the task needs to be completed by.
     */
    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format(DEADLINE_STRING_FORMAT, super.toString(), by.format(DATE_TIME_FORMATTER));
    }

    @Override
    public String toOutputFormat() {
        return String.format(DEADLINE_OUTPUT_FORMAT, super.toOutputFormat(), by.format(DATE_TIME_FORMATTER));
    }
}
