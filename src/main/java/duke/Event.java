package duke;

import java.time.LocalDateTime;

/**
 * class for Events
 */
public class Event extends Task {
    protected LocalDateTime place;

    /**
     * constructor for Event
     *
     * @param d task description
     * @param place location of task to be held at
     */
    public Event(String d, LocalDateTime place) {
        super(d);
        this.place = place;
        this.type = "E";
    }

    /**
     * Constructor for deadline
     *
     * @param d task description
     * @param done location of task to be held at
     * @param place date of the event
     */
    public Event(String d, String done, LocalDateTime place) {
        super(d, done);
        this.place = place;
        this.type = "E";
    }

    /**
     * Method to return the location of task to be held at
     *
     * @return A string representing the location of task
     */
    public String getPlace() {
        return place.format(outDtf);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getPlace() + ") ";
    }
}
