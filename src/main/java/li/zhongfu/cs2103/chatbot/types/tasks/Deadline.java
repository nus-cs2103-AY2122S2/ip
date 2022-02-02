package li.zhongfu.cs2103.chatbot.types.tasks;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A representation of a deadline item in a task list, which also contains a date and time for the deadline.
 */
public class Deadline extends Task {
    private LocalDateTime eventTime;

    /**
     * Creates a new deadline task item with the specified task name and deadline date and time.
     * 
     * @param name the name of the task
     * @param eventTime the date and time of the deadilne
     */
    public Deadline(String name, LocalDateTime eventTime) {
        super(name);
        this.eventTime = eventTime;
    }

    /**
     * Returns the date and time representing the deadline of this task item.
     * 
     * @return the date and time representing the deadline of this task item.
     */
    public LocalDateTime getDeadline() {
        return this.eventTime;
    }

    /**
     * Returns a string representation of this deadline task item.
     * 
     * @returns a string representation of this deadline task item
     */
    @Override
    public String toString() {
        return String.format(
                "[D][%s] %s (by: %s)",
                this.getDone() ? "X" : " ",
                this.getName(),
                this.getDeadline().format(DATE_TIME_FORMAT));
    }

    /**
     * Indicates whether the Object {@code o} is equal to this Deadline.
     * 
     * @param o the Object to compare this Deadline against
     * @returns true if {@code o} is equal to this Deadline, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Deadline) {
            Deadline deadline = (Deadline) o;
            return super.equals(deadline) && this.eventTime.equals(deadline.eventTime);
        }
        return false;
    }

    /**
     * Returns a hash code value for this Deadline.
     * 
     * @returns a hash code value for this Deadline
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.eventTime);
    }
}
