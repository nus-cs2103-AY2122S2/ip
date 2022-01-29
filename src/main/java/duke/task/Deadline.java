package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline Task
 */
public class Deadline extends Task {

    private LocalDateTime dueDate;

    /**
     * Constructor to create a Deadline object
     * @param description description of task
     * @param dueDate due date of task
     * @param fromDisk boolean to determine if object was created from hard disk or from commandline
     */
    public Deadline(String description, String dueDate, boolean fromDisk) {
        super(description);
        if (fromDisk) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hhmma");
            LocalDateTime parsedDate = LocalDateTime.parse(dueDate, formatter);
            this.dueDate = parsedDate;
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime parsedDate = LocalDateTime.parse(dueDate, formatter);
            this.dueDate = parsedDate;
        }
    }

    /**
     * Override toString function to display task in required syntax
     * @return String in required syntax
     */
    public String toString() {
        return "[D]" + super.toString() +
                " (by: " + dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy hhmma")) + ")";
    }
}
