package duke;

import java.time.LocalDate;
import java.time.LocalTime;


/**
 * Event task
 */
class Event extends Task implements DateTimeInterface {
    private final LocalDate date;

    public Event(String task, LocalDate date) {
        super(task, "E");
        this.date = date;
    }

    public Event(String task, boolean complete, LocalDate date) {
        super(task, "E", complete);
        this.date = date;
    }

    @Override
    public String toString() {
        if (super.isCompleted()) {
            return "[E][x] " + super.getTaskName() + " (at: " + this.date + ")";
        } else {
            return "[E][ ] " + super.getTaskName() + " (at: " + this.date + ")";
        }
    }

    @Override
    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public LocalTime getTime() {
        return null;
    }
}
