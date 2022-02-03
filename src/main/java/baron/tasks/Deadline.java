package baron.tasks;

import java.time.LocalDateTime;

import baron.util.DateTimeUtil;

/**
 * Represents a task with a deadline that consists of one additional characteristic: 'by' when
 * the task should be finished. Inherited from {@code Task}.
 *
 * @see Task
 */
public class Deadline extends Task {
    private final LocalDateTime by;

    /**
     * Constructs a {@code Deadline} object with the specified description and deadline (by).
     *
     * @param description the description of this task.
     * @param by          the deadline of LocalDateTime type that the task should be finished by.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of this {@code Deadline} task.
     *
     * @return the string representation of this {@code Deadline} task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTimeUtil.getDisplayString(this.by) + ")";
    }

    /**
     * Returns the encoded string of this {@code Deadline} task to be stored in the storage
     * using the specified delimiter.
     *
     * @param delimiter the string to be inserted in between various information of this task.
     * @return the encoded string of this {@code Deadline} task to be stored in the storage
     *         using the specified delimiter.
     */
    @Override
    public String toSaveString(String delimiter) {
        return "D" + delimiter + super.toSaveString(delimiter) + delimiter + DateTimeUtil.getSaveString(this.by);
    }
}
