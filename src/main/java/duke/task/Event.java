package duke.task;

/**
 * Represents a task that occurs on a particular date and/or time or within a given timeslot
 * Note: the allowable formats for date and time to be interpreted correctly by Event is YYYY/MM/DD (with
 * ./| being valid separators) and HHMM.
 *
 * @author  Elumalai Oviya Dharshini
 * @version 0.1
 */
public class Event extends TaskWithDateTime {
    protected String at;

    /**
     * Constructor for Event that initializes the Event with a given description and dateTime string.
     *
     * @param description description of Event
     * @param at dateTime string associated with Event
     */
    public Event(String description, String at) {
        super(description, at);
        this.at = at;
    }

    /**
     * Default toString method that returns the description, dateTime of Event with its completion status and
     * Event marker.
     *
     * @return formatted string of the description, dateTime and completeness status of Event with an Event marker
     */
    @Override
    public String toString() {
        return "[E]" + super.toString();
    }

    /**
     * Parses contents of Event into a csv-like format delimited by '|'
     *
     * @return formatted string of Event, its dateTime, completion status and an Event marker
     */
    @Override
    public String writeToFile() {
        return "E | " + super.writeToFile();
    }
}
