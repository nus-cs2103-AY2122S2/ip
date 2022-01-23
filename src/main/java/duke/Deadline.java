package duke;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Deadline task
 */
class Deadline extends Task implements DateTimeInterface {
    private final LocalDate date;

    public Deadline(String task, LocalDate date) {
        super(task, "D");
        this.date = date;
    }

    public Deadline(String task, boolean complete, LocalDate date) {
        super(task, "D", complete);
        this.date = date;
    }

    @Override
    public String toString() {
        if (super.isCompleted()) {
            return "[D][x] " + super.getTaskName() + " (by: " + this.date.toString() + ")";
        } else {
            return "[D][ ] " + super.getTaskName() + " (by: " + this.date.toString() + ")";
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
