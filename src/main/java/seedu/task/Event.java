package seedu.task;

import java.time.LocalDateTime;

public class Event extends Task {

    private LocalDateTime atDateTime;
    private static final String TYPE = "E";

    public Event(String description, LocalDateTime atDateTime) {
        super(description, TYPE);
        this.atDateTime = atDateTime;
    }

    public Event(String description, boolean isCompleted, LocalDateTime atDateTime, int priority) {
        super(description, isCompleted, priority, TYPE);
        this.atDateTime = atDateTime;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " at " + atDateTime.format(DATE_FORMAT);
    }

    @Override
    public String toFile() {
        return super.toFile() + "\t" + atDateTime.format(DATE_FORMAT);
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + atDateTime.format(DATE_FORMAT) + ")";
    }
}
