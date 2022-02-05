package duke;

/**
 * class for Events
 */
public class Event extends Task {
    protected String place;

    /**
     * constructor for Event
     * @param d task description
     * @param place location of task to be held at
     */
    public Event(String d, String place) {
        super(d);
        this.place = place;
        this.type = "E";
    }

    /**
     * Constructor for deadline
     * @param d task description
     * @param done check if task is done
     * @param place location of task to be held at
     */
    public Event(String d, String done, String place) {
        super(d, done);
        this.place = place;
        this.type = "E";
    }

    /**
     * Method to return the location of task to be held at
     * @return A string representing the location of task
     */
    public String getPlace() {
        return this.place;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + place + ") ";
    }
}

