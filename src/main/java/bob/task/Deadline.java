package bob.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * {@inheritDoc}
 */
public class Deadline extends Task {
    private LocalDateTime date;

    /**
     * Constructor for a task with a deadline.
     * @param name name of the task
     * @param dateTime dateTime of the deadline
     */
    public Deadline(String name, LocalDateTime dateTime) {
        super(name);
        super.setType("D");
        super.setStatus(0);
        this.date = dateTime;
    }

    @Override
    public String printStatus() {
        return "[D] " + Task.statusSymbols[super.getStatus()] + " " + this + " (by: "
                + date.format(DateTimeFormatter.ofPattern("dd MMM yyy HH:mm")) + ")";
    }

    @Override
    public String toString() {
        return super.getName();
    }
}
