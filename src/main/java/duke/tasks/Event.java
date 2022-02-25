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
    private LocalDate date;

    /**
     * Instantiates a new Event object
     *
     * @param rank        the rank of the Event object
     * @param description the event description
     * @param date        the date of event
     * @throws DateTimeParseException thrown when user does not
     * input date of event in a valid format
     */
    public Event(int rank, String description, LocalDate date) throws DateTimeParseException {
        super(rank, description);
        this.date = date;
    }

    /**
     * String representation of Event task
     *
     * @return the string representation of the event task
     */
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");
        return "[E]" + super.toString() + " (at: " + formatter.format(this.date) + ")";
    }
}
