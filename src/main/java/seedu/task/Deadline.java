package seedu.task;

import java.time.LocalDateTime;

/**
 * The deadline class, a subclass of Task
 */
public class Deadline extends Task {

    private static final String TYPE = "D";
    private LocalDateTime deadline;

    /**
     * Deadline constructor
     *
     * @param description Description of the task
     * @param deadline Deadline of the task
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description, TYPE);
        this.deadline = deadline;
    }

    /**
     * Overloaded constructor with more parameters added
     *
     * @param description Description of the task
     * @param isCompleted Whether the task is completed
     * @param deadline Deadline of the task
     * @param priority The priority level of the task
     */
    public Deadline(String description, boolean isCompleted, LocalDateTime deadline, int priority) {
        super(description, isCompleted, priority, TYPE);
        this.deadline = deadline;
    }

    /**
     * Adds the date to the description
     *
     * @return The description of the task with the deadline added
     */
    @Override
    public String getDescription() {
        return super.getDescription() + " by " + deadline.format(DATE_FORMAT);
    }

    /**
     * Adds the date to the save string
     *
     * @return Task as a save string to be saved in a save file
     */
    @Override
    public String toFile() {
        return super.toFile() + "\t" + deadline.format(DATE_FORMAT);
    }

    /**
     * Adds the date to the string
     *
     * @return The task as a string with the date added
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + deadline.format(DATE_FORMAT) + ")";
    }
}
