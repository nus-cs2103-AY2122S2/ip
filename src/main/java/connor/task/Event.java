package connor.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a {@code Task} of type {@code Event}.
 */
public class Event extends Task {
    protected String at;
    protected LocalDate date;
    protected boolean hasLocalDate = false;

    /**
     * Constructor for {@code Event} class.
     *
     * @param desc Description of the task.
     * @param at Event occasion of the task.
     */
    public Event(String desc, String at) {
        super(desc, TaskType.EVENT);
        this.at = at;
    }

    /**
     * Another constructor for {@code Event} class.
     *
     * @param desc Description of the task.
     * @param date Event date of the task formatted in the ISO-8601 system.
     * @see LocalDate
     */
    public Event(String desc, LocalDate date) {
        super(desc, TaskType.EVENT);
        this.date = date;
        this.at = date.toString();
        this.hasLocalDate = true;
    }

    public String getAt() {
        return at;
    }

    public boolean hasLocalDate() {
        return hasLocalDate;
    }

    /**
     * Formats the date in an easier to read format if {@code Event} uses {@code LocalDate}.
     * Returns a standard {@code String} deadline otherwise.
     *
     * @return {@code String} representation of either the LocalDate stored or a standard
     * {@code String} event occasion.
     */
    public String getDate() {
        if (hasLocalDate) {
            // Of the form "June 24, 2019"
            return date.format(DateTimeFormatter.ofPattern("MMMM d, yyyy"));
        } else {
            return at;
        }
    }

    /**
     * Returns a {@code String} representation of a {@code Event Task}.
     *
     * @return A {@code String} representation of a {@code Event Task}.
     */
    @Override
    public String toString() {
        return getType() + super.toString() + " (At: " + getDate() + ")";
    }

    /**
     * Compares the Event object with another object and returns {@code true}
     * if and only if their descriptions, event occasions and statuses are the same.
     *
     * @param o Object to compare with.
     * @return {@code true} if and only if the two Events have the same
     * description, event occasion and status, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Event)) {
            return false;
        } else {
            Event e = (Event) o;
            return this.getDesc().equals(e.getDesc())
                    && this.isDone().equals(e.isDone())
                    && this.getDate().equals(e.getDate());
        }
    }
}
