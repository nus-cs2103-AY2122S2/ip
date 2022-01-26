package main.java.duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Event extends Task {
    protected LocalDateTime at;

    /**
     * Constructs an instance of Event.
     *
     * @param description Description of task.
     * @param at Time which event occursa.
     */
    public Event(String description, String at) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.at = LocalDateTime.parse(at, formatter);
    }

    /**
     * Converts Event to string format.
     *
     * @return Event in string format.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }

    /**
     * Converts Event to file format.
     *
     * @return Event in file format.
     */
    public String toFileFormat() {
        return "E," + String.valueOf(isDone) + "," + description + "," + at.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
