package duke.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event is a subclass of Task that contains an additional LocalDateTime variable
 */
public class Event extends Task {
    private LocalDateTime dateTime;

    public Event(String description, LocalDateTime dateTime) {
        super("E", description);
        this.dateTime = dateTime;
    }

    public LocalDateTime getEventDate() {
        return this.dateTime;
    }

    /**
     * Generates a String representation of the Event object
     * @return String representation of the Event object
     */
    @Override
    public String toString() {
        return super.toString() + "(at: " +
                this.dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a")) + ")";
    }

    /**
     * Generates the String to be stored in the database
     * @return String text formatted for database
     */
    @Override
    public String dBText() {
        String complete = this.getCompleted() ? "1" : "0";
        return String.format("E|%s|%s|%s", complete, this.getDescription(), this.dateTime.toString());
    }

}
