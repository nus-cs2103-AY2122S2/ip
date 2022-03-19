package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * tasks that STARTS at a specific time and ENDS at a specific time.
 */
public class Events extends Task {

    public String at;
    public LocalDate date;

    public Events(String description, String at) {
        super(description);
        this.at = at;

        try {
            this.date = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            // shouldnt set the date if we didnt receive it in the right format
        }
    }

    /**
     * Overloaded constructor to accept localDate.
     */
    public Events(String description, LocalDate date) {
        super(description);
        this.at = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + super.getStatusIcon() + "] " + super.getDescription() + " (at: " + at + ")";
    }
}
