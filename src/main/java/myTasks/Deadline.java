package mytasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

/**
 * Creates a task that must be completed before a certain time.
 */
public class Deadline extends Task {
    private String dateTime;

    /**
     * Constructor for a deadline task.
     * @param description The description of a deadline.
     * @param dateTime The date and time of a deadline.
     */
    public Deadline(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Return the dat and time of the deadline.
     * @return date and time of the deadline
     */
    public String returnDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: "
                + LocalDateTime.parse(dateTime, new DateTimeFormatterBuilder()
                    .parseCaseInsensitive()
                    .appendPattern("yyyy-MM-dd hha")
                    .toFormatter())
                    .format(DateTimeFormatter.ofPattern("MMMM d, yyyy hha")) + ")";
    }
}
