package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Inherits from the Task class.
 * Stores the description and date of an Event object.
 * Provides to String method to print event details in specific format.
 */
public class Event extends Task {

    protected LocalDate at;

    public Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = LocalDate.parse(at, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " - at: " + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
}