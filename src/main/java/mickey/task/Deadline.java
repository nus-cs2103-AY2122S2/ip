package mickey.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deadline task.
 */
public class Deadline extends Task {
    /**
     * Task deadline date.
     */
    private final LocalDateTime by;

    /**
     * Constructor.
     *
     * @param description Task description.
     * @param date Task deadline date.
     * @throws DateTimeParseException DateTime parse error.
     */
    public Deadline(String description, String date) throws DateTimeParseException {
        super(description);
        this.by = LocalDateTime.parse(date, (DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
    }

    /**
     * Gets deadline by date.
     *
     * @return deadline by date.
     */
    public LocalDateTime getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"))
                + ")";
    }
}
