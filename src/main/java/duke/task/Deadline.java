package duke.task;

import duke.exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline Task
 */
public class Deadline extends Task {

    private LocalDateTime dueDate;

    /**
     * Constructor to create a Deadline object
     * @param description description of task
     * @param dueDate due date of task
     * @param priority priority level
     * @param fromDisk boolean to determine if object was created from hard disk or from commandline
     */
    public Deadline(String description, String dueDate, String priority, boolean fromDisk) {
        super(description, priority);
        if (fromDisk) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hhmma");
            LocalDateTime parsedDate = LocalDateTime.parse(dueDate, formatter);
            this.dueDate = parsedDate;
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            try {
                LocalDateTime parsedDate = LocalDateTime.parse(dueDate, formatter);
                this.dueDate = parsedDate;
            } catch (DateTimeParseException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Override toString function to display task in required syntax
     * @return String in required syntax
     */
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy hhmma")) + ")";
    }
}
