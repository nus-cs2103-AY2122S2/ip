package connor.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a {@code Task} of type {@code Deadline}.
 */
public class Deadline extends Task {
    protected String by;
    protected LocalDate date;
    protected boolean hasLocalDate = false;

    /**
     * Constructor for {@code Deadline} class.
     *
     * @param desc Description of the task.
     * @param by Deadline of the task.
     */
    public Deadline(String desc, String by) {
        super(desc, TaskType.DEADLINE);
        this.by = by;
    }

    /**
     * Another constructor for {@code Deadline} class.
     *
     * @param desc Description of the task.
     * @param date Deadline date of the task formatted in the ISO-8601 system.
     * @see LocalDate
     */
    public Deadline(String desc, LocalDate date) {
        super(desc, TaskType.DEADLINE);
        this.date = date;
        this.by = date.toString();
        this.hasLocalDate = true;
    }

    public String getBy() {
        return by;
    }

    public boolean hasLocalDate() {
        return hasLocalDate;
    }

    /**
     * Formats the date in an easier to read format if {@code Deadline} uses {@code LocalDate}.
     * Returns a standard {@code String} deadline otherwise.
     *
     * @return {@code String} representation of either the LocalDate stored or a standard
     * {@code String} deadline.
     */
    private String getDate() {
        if (hasLocalDate) {
            // Of the form "June 24, 2019"
            return date.format(DateTimeFormatter.ofPattern("MMMM d, yyyy"));
        } else {
            return by;
        }
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
