package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Event deadline.
     *
     * @param task tasks for deadline.
     * @param by time by.
     */
    public Deadline(String task, LocalDate by) {
        super(task.trim());
        this.by = by;
    }

    /**
     * Deadline class change done status.
     *
     * @param task tasks for deadline.
     * @param by time by.
     * @param done done status.
     */
    public Deadline(String task, LocalDate by, boolean done) {
        super(task, done);
        this.by = by;
    }

    @Override
    public Deadline mark() {
        return new Deadline(task, by, true);
    }

    @Override
    public Deadline unmark() {
        return new Deadline(task, by, false);
    }

    @Override
    public String saveData() {
        int done = super.done ? 1 : 0;
        return Type.D + " | " + done + " | " + task + " | " + by;
    }

    @Override
    public LocalDate getDate() {
        return by;
    }

    @Override
    public String toString() {
        String outputDate = by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[" + Type.D + "]" + super.toString()
                + " (by: " + outputDate + ")";
    }
}
