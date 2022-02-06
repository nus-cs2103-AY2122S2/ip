package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline type of task.
 */
public class Deadline extends Task {
    protected LocalDate deadlineTime;

    /**
     * Constructor for Deadline.
     * @param description the description for the task.
     * @param isDone status of the task.
     * @param deadlineTime deadline time for the task.
     */
    public Deadline(String description, boolean isDone, LocalDate deadlineTime) {
        super(description, isDone);
        this.deadlineTime = deadlineTime;
    }

    /**
     * Another constructor for Deadline.
     * @param description the description for the task.
     * @param DeadlineTime deadline time for the task.
     */
    public Deadline(String description, LocalDate DeadlineTime) {
        this(description, false, DeadlineTime);
    }

    /**
     * Get the saved message corresponding to this task.
     * @return save message for the task.
     */
    @Override
    public String getSaveMessage() {
        if (this.isDone) {
            return String.format("D / 1 / %s / %s", this.description, this.deadlineTime);
        } else {
            return String.format("D / 0 / %s / %s", this.description, this.deadlineTime);
        }
    }

    @Override
    public String toString() {
        String timeString = this.deadlineTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        if (this.isDone) {
            return String.format("[D][X] %s (by: %s)", this.description, timeString);
        } else {
            return String.format("[D][ ] %s (by: %s)", this.description, timeString);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Deadline)) {
            return false;
        }

        Deadline deadline = (Deadline) o;

        return deadline.description.equals(this.description);
    }
}
