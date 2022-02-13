package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDate by;

    /**
     * Represents Deadline.
     *
     * @param task tasks for deadline.
     * @param by   time by.
     */
    public Deadline(String task, LocalDate by) {
        super(task.trim());
        this.by = by;
    }

    /**
     * Changes done status by generating new Deadline.
     *
     * @param task   tasks for deadline.
     * @param by     time by.
     * @param isDone done status.
     */
    public Deadline(String task, LocalDate by, boolean isDone) {
        super(task, isDone);
        this.by = by;
    }

    /**
     * Changes status by generating new Deadline.
     *
     * @param task     tasks for task.
     * @param isDone   done status.
     * @param by       time by.
     * @param priority priority status.
     */
    public Deadline(String task, LocalDate by, boolean isDone, Priority priority) {
        super(task, isDone, priority);
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
    public Deadline setPriority(Priority priority) {
        boolean isDone = super.getDoneStatus() == 1;
        return new Deadline(super.getTask(), by, isDone, priority);
    }

    @Override
    public String saveData() {
        int isDone = super.getDoneStatus();
        return Type.D + " | " + isDone + " | " + super.getPriorityText() + " | " + super.getTask() + " | " + by;
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
