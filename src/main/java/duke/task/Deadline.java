package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline, with a specific date and time.
 */
public class Deadline extends Task {

    protected LocalDateTime by;
    protected String type;

    /**
     * Class constructor.
     *
     * @param description The description of the deadline.
     * @param by The date and time of the deadline.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
        this.type = "D";
    }

    /**
     * Returns the string representation of the deadline.
     *
     * @return The string representation of the status, description and datetime of the deadline.
     */
    @Override
    public String toString() {
        String formattedDateTime = by.format(DateTimeFormatter.ofPattern("d MMM yyyy, h:mm a"));
        return "[D]" + super.toString() + " (by: " + formattedDateTime + ")";
    }

    /**
     * Returns the type of the deadline task.
     *
     * @return "D", which is the short notation of a deadline type.
     */
    @Override
    public String getType() {
        return this.type;
    }

    /**
     * Returns the description and datetime of the deadline.
     *
     * @return The string representation of the deadline of format: description | datetime
     */
    @Override
    public String getDescription() {
        return this.description + " | " + by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
