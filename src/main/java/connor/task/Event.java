package connor.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a {@code Task} of type {@code Event}.
 */
public class Event extends Task {
    protected LocalDateTime dateTime;

    /**
     * Constructor for {@code Event} class.
     *
     * @param desc Description of the task.
     * @param dateTime Deadline date and time of the task.
     * @see LocalDateTime
     */
    public Event(String desc, LocalDateTime dateTime) {
        super(desc, TaskType.EVENT);
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

    public LocalDateTime getDateTime() {
        return dateTime;
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
