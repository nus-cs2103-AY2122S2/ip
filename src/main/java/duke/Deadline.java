package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    private final LocalDate by;

    /**
     * Constructs an instance of a deadline, which is unmarked by default.
     *
     * @param description A string representing the task description.
     * @param by A String representing a time in the format of "yyyy-mm-dd"
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * Constructs an instance of a marked or unmarked deadline.
     *
     * @param description A string representing the task description.
     * @param by A String representing a time in the format of "yyyy-mm-dd".
     * @param isDone A boolean representing whether the task is marked as done.
     */
    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Formats the date of the deadline
     *
     * @return A string representation of the date in the format of "MMM d yyyy".
     */
    public String formatDate() {
        return this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task mark() {
        return new Deadline(this.getDescription(), this.by, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task unmark() {
        return new Deadline(this.getDescription(), this.by, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.formatDate() + ")";
    }
}
