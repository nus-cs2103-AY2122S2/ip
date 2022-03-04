package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * constructor duke.Deadline object
     * @param String description of what deadline
     * @parm LocalDateTime by object which stores the date and time of that event involved
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }
    /**
     * Gets the string of the date in format
     * @param LocalDateTime object
     */
    public String getBy() {
        String formattedDate = by.format(DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a"));
        return formattedDate;
    }

    @Override
    public String toString() {
        String formattedDate = by.format(DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a"));
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }

}
 