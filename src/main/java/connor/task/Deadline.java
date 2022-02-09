package connor.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a {@code Task} of type {@code Deadline}.
 */
public class Deadline extends Task {
    protected LocalDateTime dateTime;

    /**
     * Constructor for {@code Deadline} class.
     *
     * @param desc Description of the task.
     * @param dateTime Deadline date and time of the task.
     * @see LocalDateTime
     */
    public Deadline(String desc, LocalDateTime dateTime) {
        super(desc, TaskType.DEADLINE);
        this.dateTime = dateTime;
    }

    /**
     * Formats the date in an easier to read format.
     *
     * @return {@code String} representation of the LocalDate stored.
     */
    private String getDate() {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mma"));
    }

    public String getStorageDate() {
        return dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    /**
     * Returns a {@code String} representation of a {@code Deadline Task}.
     *
     * @return A {@code String} representation of a {@code Deadline Task}.
     */
    @Override
    public String toString() {
        return getType() + super.toString() + " (By: " + getDate() + ")";
    }

    /**
     * Compares the Deadline object with another object and returns {@code true}
     * if and only if their descriptions, deadlines and statuses are the same.
     *
     * @param o Object to compare with.
     * @return {@code true} if and only if the two Deadlines have the same
     * description, deadline and status, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Deadline)) {
            return false;
        } else {
            Deadline d = (Deadline) o;
            return this.getDesc().equals(d.getDesc())
                    && this.isDone().equals(d.isDone())
                    && this.getDate().equals(d.getDate());
        }
    }
}
