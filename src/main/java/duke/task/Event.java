package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDate at;

    /**
     * Represents task Event class.
     *
     * @param task tasks for event.
     * @param at   time at.
     */
    public Event(String task, LocalDate at) {
        super(task.trim());
        this.at = at;
    }

    /**
     * Changes done status by generating new Task.
     *
     * @param task   tasks for event.
     * @param at     time at.
     * @param isDone done status.
     */
    public Event(String task, LocalDate at, boolean isDone) {
        super(task, isDone);
        this.at = at;
    }

    /**
     * Changes status by generating new Deadline.
     *
     * @param task     tasks for task.
     * @param isDone   done status.
     * @param at       time at.
     * @param priority priority status.
     */
    public Event(String task, LocalDate at, boolean isDone, Priority priority) {
        super(task, isDone, priority);
        this.at = at;
    }

    @Override
    public Event mark() {
        return new Event(super.getTask(), at, true);
    }

    @Override
    public Event unmark() {
        return new Event(super.getTask(), at, false);
    }

    @Override
    public Event setPriority(Priority priority) {
        boolean isDone = super.getDoneStatus() == 1;
        return new Event(super.getTask(), at, isDone, priority);
    }

    @Override
    public String saveData() {
        int isDone = super.getDoneStatus();
        return Type.E + " | " + isDone + " | " + super.getPriorityText() + " | " + super.getTask() + " | " + at;
    }

    @Override
    public LocalDate getDate() {
        return at;
    }

    @Override
    public String toString() {
        String outputDate = at.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[" + Type.E + "]" + super.toString()
                + " (at: " + outputDate + ")";
    }
}
