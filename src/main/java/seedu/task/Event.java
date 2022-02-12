package seedu.task;

import java.time.LocalDateTime;

/**
 * The Event Class of Task
 */
public class Event extends Task {

    private LocalDateTime atDateTime;

    public Event(String description, LocalDateTime atDateTime) {
        super(description);
        this.atDateTime = atDateTime;
    }

    public Event(String description, boolean isCompleted, LocalDateTime atDateTime, int priority) {
        super(description, isCompleted, priority);
        this.atDateTime = atDateTime;
    }

    @Override
    public String toFile() {
        return "E\t" + super.toFile() + "\t" + atDateTime.format(DATE_FORMAT);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (by: " + atDateTime.format(DATE_FORMAT) + ")";
    }
}
