package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Inherits from the Task class.
 * Stores the description and deadline (date) of a Deadline object.
 * Provides to String method to print deadline details in specific format.
 */
public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " - by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
}