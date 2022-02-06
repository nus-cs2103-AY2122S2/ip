package bob;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with an associated time.
 */
public class Event extends bob.Task {
    protected LocalDateTime time;

    /**
     * Constructor for a Event object, given description and a time in parseable format.
     *
     * @param description Description of the Event instance.
     * @param time        A string representing time in parseable format.
     */
    public Event(String description, String time) {
        super(description);
        this.time = LocalDateTime.parse(time);
    }

    /**
     * Constructor for a Event object, given description, time and status.
     *
     * @param description Description of the Event instance.
     * @param time        A string representing time in parseable format.
     * @param isDone      Status of the Event instance.
     */
    public Event(String description, String time, boolean isDone) {
        super(description, isDone);
        this.time = LocalDateTime.parse(time);
    }

    /**
     * Generates a string representing this Task object for saving.
     *
     * @return A string representing this Task for saving.
     */
    @Override
    public String generateSavedEntry() {
        return String.format("E|%s|%s", isDone ? "1" : "0", description + "/at "
                + time.format(DateTimeFormatter.ISO_DATE_TIME));
    }

    /**
     * Returns a string representation of this Task for display.
     *
     * @return A string representing this task for display.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(),
                time.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
