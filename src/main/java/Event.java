public class Event extends Task {

    /** Stores the time span of the event. */
    private String duration;

    /**
     * Constructor of Event class.
     * @param description The description of the event.
     * @param duration The time span of the event.
     */
    public Event(String description, String duration) {
        super(description);
        this.duration = duration;
    }

    /**
     * Returns the task in proper format.
     * @return String of the Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.duration + ")";
    }
}
