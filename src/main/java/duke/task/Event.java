package duke.task;

/**
 * Represents a task that occurs at a particular date and/or time.
 * Note: the allowable formats for date and time to be interpreted correctly
 * by Event is YYYY/MM/DD (with * ./| being valid separators) and HHMM.
 *
 * @author  Elumalai Oviya Dharshini
 * @version 1.0
 */
public class Event extends TaskWithDateTime {

    /**
     * Constructor for Event specifying description, dateTime string.
     *
     * @param description description of Event
     * @param at dateTime string associated with Event
     */
    public Event(String description, String at) {
        super(description, at);
    }

    /**
     * Default toString method that returns formatted Event.
     *
     * @return formatted string of the description, dateTime and completeness
     * status of Event with an Event marker.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString();
    }

    /**
     * Parses contents of Event into a csv-like format.
     * Delimiter is '|'.
     *
     * @return formatted string of Event, its dateTime, completion status
     * and an Event marker.
     */
    @Override
    public String writeToFile() {
        return "E | " + super.writeToFile();
    }
}