package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates a task with an event date
 */
public class Event extends Task {
    /**
     * The date of the event
     */
    private final LocalDate eventDate;

    /**
     * Instantiates a new Event object
     *
     * @param taskDescription the event description
     * @param eventDate        the date of event
     * @throws DateTimeParseException thrown when user does not
     * input date of event in a valid format
     */
    public Event(String taskDescription, LocalDate eventDate) throws DateTimeParseException {
        super(taskDescription);
        this.eventDate = eventDate;
    }

    /**
     * String representation of Event task
     *
     * @return the string representation of the event task
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");
        return "[E]" + super.toString() + " (at: " + formatter.format(this.eventDate) + ")";
    }
}
