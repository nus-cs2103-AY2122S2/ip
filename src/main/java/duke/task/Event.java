package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate at;

    public Event(String task, LocalDate at) {
        super(task.trim());
        this.at = at;
    }

    public Event(String task, LocalDate at, boolean done) {
        super(task, done);
        this.at = at;
    }

    @Override
    public Event mark() {
        return new Event(task, at, true);
    }

    @Override
    public Event unmark() {
        return new Event(task, at, false);
    }

    @Override
    public String saveData() {
        int done = super.done ? 1 : 0;
        return Type.E + " | " + done + " | " + task + " | " + at;
    }

    @Override
    public LocalDate getDate() {
        return at;
    }

    @Override
    public String toString() {
        return "[" + Type.E + "]" + super.toString() + " (at: "
                + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
