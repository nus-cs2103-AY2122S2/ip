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
     * Checks if this {@code Deadline} object is equal to the specified object.
     *
     * @param o an {@code Object} to be compared with.
     * @return true if the specified object equals to this {@code Deadline} object.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (!(o instanceof Deadline)) {
            return false;
        }

        Deadline deadline = (Deadline) o;
        boolean isDescriptionEqual = this.description.equals(deadline.description);
        boolean isByEqual = this.by.equals(deadline.by);

        return isDescriptionEqual && isByEqual;
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
