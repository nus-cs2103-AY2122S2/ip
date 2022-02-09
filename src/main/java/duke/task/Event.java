package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event class.
 */
public class Event extends Task {

    private LocalDate date;

    /**
     * Constructs event.
     * @param description Task description.
     * @param isMarked Indicates whether task is marked/done.
     * @param date Event date
     */
    public Event(String description, boolean status, LocalDate date) {
        super(description, status);
        this.date = date;
    }

    /**
     * Constructs event.
     * @param description Task description.
     * @param date Event date
     */
    public Event(String description, LocalDate date) {
        this(description, false, date);
    }

    /**
     * Returns string representation of an event.
     * @return Event as a string
     */
    public String toString() {
        return String.format("[E][%s] %s (at: %s)",
                super.isMarked(),
                super.getDescription(),
                DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
