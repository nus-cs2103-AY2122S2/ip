package tasks;

import java.time.LocalDateTime;

/**
 * This class encapsulates a Deadline Task.
 *
 * A Deadline task has a specific time that it has to be done by.
 *
 * @author Ong Han Yang
 */
public class DeadlineTask extends Task {
    /** The deadline for the task.*/
    private LocalDateTime deadline;

    /**
     * Constructs a Deadline task.
     *
     * @param desc the description for the task.
     * @param deadline the deadline for the task.
     */
    public DeadlineTask(String desc, LocalDateTime deadline) {
        super(desc);
        this.deadline = deadline;
    }

    /**
     * Produces a Deadline task.
     *
     * @param desc the description of the task.
     * @param deadline the deadline of the task.
     * @return the deadline task.
     */
    public static DeadlineTask of(String desc, LocalDateTime deadline) {
        return new DeadlineTask(desc, deadline);
    }

    /**
     * Represents the Deadline task as a string.
     *
     * @return String representation of the deadline task.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s %s)",
                super.toString(),
                deadline.toLocalDate(),
                deadline.toLocalTime());
    }
}
