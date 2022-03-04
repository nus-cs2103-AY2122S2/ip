package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    LocalDateTime at;

    /**
     * Constructor for duke.Event object
     * @param String description of what event
     * @parm LocalDateTime by object which stores the date and time of that event involved
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Gets the string of the date in format
     * @param LocalDateTime object
     */
    public String getAt() {
        String formattedDate = at.format(DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a"));
        return formattedDate;
    }

    @Override
    public String toString() {
        String formattedDate = at.format(DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a"));
        return "[E]" + super.toString() + " (at: " + formattedDate + ")";
    }

}