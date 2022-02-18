package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a event task.
 */
public class Event extends Task {
    protected LocalDate at;

    /**
     * Constructs an instance of an event, which is unmarked by default.
     *
     * @param description A string representing the task description.
     * @param at A String representing a time in the format of "yyyy-mm-dd"
     */
    public Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at);
    }

    /**
     * Constructs an instance of a marked or unmarked event.
     *
     * @param description A string representing the task description.
     * @param at A String representing a time in the format of "yyyy-mm-dd".
     * @param isDone A boolean representing whether the task is marked as done.
     */
    public Event(String description, LocalDate at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Formats the date of the event
     *
     * @return A string representation of the date in the format of "MMM d yyyy".
     */
    String formatDate() {
        return this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task mark() {
        return new Event(this.getDescription(), this.at, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task unmark() {
        return new Event(this.getDescription(), this.at, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.formatDate() + ")";
    }
}
