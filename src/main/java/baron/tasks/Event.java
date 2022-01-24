package baron.tasks;

import baron.util.DateTimeUtil;

import java.time.LocalDateTime;

public class Event extends Task {
    private final LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + DateTimeUtil.getDisplayString(this.at) + ")";
    }

    @Override
    public String toSaveString(String delimiter) {
        return "E" + delimiter + super.toSaveString(delimiter) + delimiter + this.at;
    }
}
