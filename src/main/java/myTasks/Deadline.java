package myTasks;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * Creates a task that must be completed before a certain time.
 */
public class Deadline extends Task {
    public String dateTime;

    public Deadline(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " +
                LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd hha"))
                    .format(DateTimeFormatter.ofPattern("MMMM d, yyyy hha")) + ")";
    }
}