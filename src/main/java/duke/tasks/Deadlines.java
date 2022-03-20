package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Tasks that need to be done before a specific time/date.
 */
public class Deadlines extends Task {

    public String by;
    LocalDate date;

    /**
     * Deadline will take in a description and a by timeline.
     */
    public Deadlines(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Overloaded constructor to accept localDate.
     */
    public Deadlines(String description, LocalDate date) {
        super(description);
        this.by = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + super.getStatusIcon() + "] " + super.getDescription() + " (by: " + by + ")";
    }

}
