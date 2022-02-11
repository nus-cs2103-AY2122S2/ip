package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event is a subclass of Task. It is a more specific Task that is a reminder of an important
 * occasion with its date attached.
 */
public class Event extends Task {

    private LocalDate date;

    /**
     * Event constructor.
     * @param details is a String containing information on what the event is.
     * @param date is a LocalDate object of the date the event will be held.
     */
    public Event(String details, LocalDate date) {
        super(details);
        this.date = date;
    }

    /**
     * Passes the Event object out in readable format.
     * @return the readable string giving the Event's details.
     */
    @Override
    public String toString() {
        return "E" + super.toString() + " | " + this.date.format(DateTimeFormatter.ofPattern("MMM dd, yyyy"));
    }

}
