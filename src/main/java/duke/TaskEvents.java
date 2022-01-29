package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents task of event type
 *
 * @author sibinhho99-nus
 */
public class TaskEvents extends Task {
    LocalDate startAt;

    /**
     * Constructor
     */
    public TaskEvents(boolean isDone, String name, String startAt) throws DateTimeParseException {
        super(isDone, name);
        this.startAt = LocalDate.parse(startAt);
    }

    /**
     * Returns String representation of the Task
     */
    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", isDone ? "X" : " ", name, startAt);
    }
}
