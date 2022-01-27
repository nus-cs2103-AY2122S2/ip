package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an instance of a Event
 * type of Task
 */
public class Event extends Task {

    /**
     * Stores the date on which this Event
     * occurs
     */
    protected LocalDate date;

    /**
     * Constructor method for Event
     *
     * @param desc Description of Event
     * @param isComp Completion Status of Event
     * @param date Date on which the Event Occurs
     */
    public Event(String desc, boolean isComp, LocalDate date) {
        super(desc, isComp);
        this.date = date;
    }

    /**
     * toString method to provide String representation
     * of Event
     *
     * @return String representation of the Event
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + this.getDate() + ")";
    }

    /**
     * Returns the date of the event
     * in MMM d yyyy format
     *
     * @return Date of Event as a String
     */
    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
