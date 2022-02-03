package seedu.task;

import java.time.LocalDateTime;

public class Event extends Task {

    private final LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    public Event(String description, boolean isCompleted, LocalDateTime at) {
        super(description, isCompleted);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (by: " + at.format(formatter) + ")";
    }
}
