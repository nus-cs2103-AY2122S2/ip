package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event class represents tasks that occur at a specific time.
 */
public class Event extends Task {
    private LocalDate at;

    /**
     * Creates an event task with the given data.
     *
     * @param description Description of the task to be created.
     * @param at Event time of the task to be created.
     */
    public Event(String description, LocalDate at) {
        super(description, TaskType.EVENT);
        this.at = at;
    }

    /**
     * Creates an event task with the given data.
     *
     * @param description Description of the task to be created.
     * @param isDone Status of the task to be created.
     * @param at Event time of the task to be created.
     */
    public Event(String description, boolean isDone, LocalDate at) {
        super(description, isDone, TaskType.EVENT);
        this.at = at;
    }

    @Override
    public LocalDate getDate() {
        return this.at;
    }

    @Override
    public String formatForFile() {
        return super.formatForFile() + " | " + this.at.toString();
    }

    /**
     * Returns type, description and event time of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
