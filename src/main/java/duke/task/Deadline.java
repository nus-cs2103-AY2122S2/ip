package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDate by;

    /**
     * Represents task deadline.
     *
     * @param task tasks for deadline.
     * @param by   time by.
     */
    public Deadline(String task, LocalDate by) {
        super(task.trim());
        this.by = by;
    }

    /**
     * Changes done status by generating new Task.
     *
     * @param task tasks for deadline.
     * @param by   time by.
     * @param isDone done status.
     */
    public Deadline(String task, LocalDate by, boolean isDone) {
        super(task, isDone);
        this.by = by;
    }

    @Override
    public Deadline mark() {
        return new Deadline(super.getTask(), by, true);
    }

    @Override
    public Deadline unmark() {
        return new Deadline(super.getTask(), by, false);
    }

    @Override
    public String saveData() {
        int isDone = super.getDoneStatus();
        return Type.D + " | " + isDone + " | " + super.getTask() + " | " + by;
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
