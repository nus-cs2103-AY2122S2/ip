package seedu.task;

import java.time.LocalDateTime;

/**
 * The event class, a subclass of Task
 */
public class Event extends Task {

    private LocalDateTime atDateTime;
    private static final String TYPE = "E";

    /**
     * Event constructor
     *
     * @param description Description of the task
     * @param atDateTime datetime of when the task begins
     */
    public Event(String description, LocalDateTime atDateTime) {
        super(description, TYPE);
        this.atDateTime = atDateTime;
    }

    /**
     * Overloaded constructor with more parameters added
     *
     * @param description Description of the task
     * @param isCompleted Whether the task is completed
     * @param atDateTime datetime of when the task begins
     * @param priority The priority level of the task
     */
    public Event(String description, boolean isCompleted, LocalDateTime atDateTime, int priority) {
        super(description, isCompleted, priority, TYPE);
        this.atDateTime = atDateTime;
    }

    /**
     * Adds the date to the description
     *
     * @return The description of the task with the deadline added
     */
    @Override
    public String getDescription() {
        return super.getDescription() + " at " + atDateTime.format(DATE_FORMAT);
    }

    /**
     * Adds the date to the save string
     *
     * @return Task as a save string to be saved in a save file
     */
    @Override
    public String toFile() {
        return super.toFile() + "\t" + atDateTime.format(DATE_FORMAT);
    }

    /**
     * Adds the date to the string
     *
     * @return The task as a string with the date added
     */
    @Override
    public String toString() {
        return super.toString() + " (at: " + atDateTime.format(DATE_FORMAT) + ")";
    }
}
