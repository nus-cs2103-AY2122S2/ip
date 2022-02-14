package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an instance of a Event
 * type of Task
 */
public class Event extends Task implements Comparable<Event> {

    /**
     * Store the date on which this Event
     * occurs
     */
    private LocalDate date;

    /**
     * Constructor method for Event
     *
     * @param desc Description of Event
     * @param isCompleted Completion Status of Event
     * @param date Date on which the Event Occurs
     */
    public Event(String desc, boolean isCompleted, LocalDate date) {
        super(desc, isCompleted);
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

    /**
     * compareTo method where Event with
     * earlier date is considered less than
     * an Event with a later date
     *
     * @param o Event Instance it is being compared to
     * @return Integer based on compareTo definition
     */
    @Override
    public int compareTo(Event o) {
        return this.date.compareTo(o.date);
    }
}
