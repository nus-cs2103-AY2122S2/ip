package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents task of event type.
 *
 * @author sibinhho99-nus
 */
public class EventTask extends Task {
    private LocalDate startAt;

    /**
     * Constructor for task of event type.
     * @param isDone whether the task is done.
     * @param name the name of the task.
     * @param startAt the start of the event.
     */
    public EventTask(boolean isDone, String name, String startAt) throws DateTimeParseException {
        super(isDone, name);
        this.startAt = LocalDate.parse(startAt);
    }

    /**
     * Returns when the event starts.
     * @return when the event starts.
     */
    public LocalDate getStartAt() {
        return startAt;
    }

    /**
     * Returns String representation of the Task.
     */
    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", this.isDone() ? "X" : " ", this.getName(), startAt);
    }
}
