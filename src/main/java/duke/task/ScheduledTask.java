package duke.task;

import java.time.LocalDate;

public abstract class ScheduledTask extends Task implements Comparable<ScheduledTask> {

    private final LocalDate date;

    /**
     * Initialises a scheduled task.
     *
     * @param description the description of the task.
     * @param date the date on which this task should be scheduled.
     */
    public ScheduledTask(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    /**
     * Returns the date on which this task was scheduled.
     *
     * @return the LocalDate on which this task was scheduled.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Checks if this task's scheduled date is earlier
     * than a given input date.
     *
     * @param date a date to compare this task's scheduled date to.
     * @return true iff this task's scheduled date is earlier than the given date.
     */
    public boolean isEarlierThan(LocalDate date) {
        return this.date.isBefore(date);
    }

    /**
     * Compares this ScheduledTask instance with another ScheduledTask instance
     * based on their chronological order.
     *
     * @param other the other ScheduledTask instance to compare to.
     * @return 1, -1, 0 if this ScheduledTask's date is after, before, or equal to the other's date.
     */
    @Override
    public int compareTo(ScheduledTask other) {
        if (this.date.isAfter(other.date)) {
            return 1;
        } else if (this.date.isBefore(other.date)) {
            return -1;
        } else {
            return 0;
        }
    }
}
