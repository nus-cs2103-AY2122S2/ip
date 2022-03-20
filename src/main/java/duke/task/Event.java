package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event class.
 */
public class Event extends Task {

    private LocalDateTime date;

    /**
     * Constructs event.
     * @param description Task description.
     * @param isMarked Indicates whether task is marked/done.
     * @param date Event date
     */
    public Event(String description, boolean status, LocalDateTime date) {
        super(description, status);
        this.date = date;
    }

    /**
     * Constructs event.
     * @param description Task description.
     * @param date Event date
     */
    public Event(String description, LocalDateTime date) {
        this(description, false, date);
    }

    /**
     * Returns string representation of an event.
     * @return Event as a string
     */
    public String toString() {
        String status = super.isMarked() ? "X" : " ";
        return String.format("[E][%s] %s (at: %s)",
                status,
                super.getDescription(),
                date.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")));
    }

    /**
     * Returns string representation of a event.
     * @return Event as a string
     */
    public String toDataString() {
        return String.format("E,%s,%s,%s",
                isMarked(),
                super.getDescription(),
                date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

}
