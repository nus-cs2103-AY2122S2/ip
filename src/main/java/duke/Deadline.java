package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Deadline task constructor
     *
     * @param description description of command
     * @param by date and time deadline is due
     * @param isDone status of task
     */
    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    public LocalDateTime getDateTime() {
        return this.by;
    }

    /**
     * Parse date into MMM dd yyyy format
     *
     * @return date in MMM dd yyyy hh:mm a format
     */
    public String parseDate() {

        return this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy 'by' hh:mm a "));
    }

    /**
     * Overrides toString method
     *
     * @return description of task and date
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " ( " + parseDate() + ")";
    }
}
