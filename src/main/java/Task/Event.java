package task;

public class Event extends Task {

    protected String at;

    /**
     * Default constructor for Event object.
     *
     * @param description description of Event to be added
     * @param at date that Event will occur at
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Prints Event details as a String.
     *
     * @return String form of Event details
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}