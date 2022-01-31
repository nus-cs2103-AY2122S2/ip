package duke;

import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime by;

    public Event(String desc, LocalDateTime by) {
        super(desc, "E");
        this.by = by;
    }
    public Event(String desc, LocalDateTime by, boolean done) {
        super(desc, done, "E");
        this.by = by;
    }

    @Override
    public LocalDateTime getBy() {
        return this.by;
    }
    @Override
    public Task mark() {
        return new Event(this.desc, this.by, true);
    }
    @Override
    public Task unmark() {
        return new Event(this.desc, this.by, false);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (by: " + by + ")";
    }
}
