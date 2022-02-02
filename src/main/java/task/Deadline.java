package task;

import main.Date;

/**
 * Represent a deadline, which is a type of task.
 * @author Fan Jue
 * @version 0.1.0
 * @since 0.1.0
 */
public class Deadline extends Task {
    protected String by;
    protected Date date;

    /**
     * Create a deadline that needs to be finished before a specific date.
     *
     * @param description A description of the deadline.
     * @param by Date by which the deadline needs to be finished.
     */
    public Deadline(String description, String by) {
        super(description);
        this.date = new Date(by);
        this.by = by;
    }

    /**
     * Convert the deadline to a string in the format of storage memory.
     *
     * @return Memory representation of the deadline.
     */
    @Override
    public String toMemoryString() {
        return "D" + super.toMemoryString() + "@" + this.by;
    }

    /**
     * Check if the deadline ends on a specific date.
     *
     * @param date An input date to check with the instance.
     * @return True if the deadline ends on the input date.
     */
    @Override
    public boolean isOn(Date date) {
        return this.date.equals(date);
    }

    /**
     * Convert the deadline to a string for UI.
     *
     * @return The UI representation of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.formattedTime() + ")";
    }
}
