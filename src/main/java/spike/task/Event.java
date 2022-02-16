package spike.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulate information of event task.
 */
public class Event extends Task {

    /**
     * Default constructor.
     */
    public Event(String description, LocalDateTime at) {
        super(description, at);
    }

    /**
     * Returns the representative string for saving in data file.
     */
    @Override
    public String toFileFormat() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
        int status = super.isDone ? 1 : 0;
        return "E" + " | " + status + " | " + super.description + " | " + dtf.format(super.dateTime);
    }

    /**
     * Returns proper string representation of the task object
     */
    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN_FOR_PRINT);
        return "[E]" + super.toString() + " (at: " + dtf.format(super.dateTime) + ")";
    }
}
