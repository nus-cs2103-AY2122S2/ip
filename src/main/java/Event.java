public class Event extends Task {
    /**
     * The time of this event
     */
    private String eventTime;

    /**
     * Constructor for Event objects
     *
     * @param eventName  the event name
     */
    public Event(String eventName, String time) {
        super(eventName);
        this.eventTime = time;
    }

    /**
     * Returns a String representation of the Event
     *
     * @return  Event in String
     */
    @Override
    public String toString() {
        String box1 = "[E]";
        String doneness;
        if (super.getDone()) {
            doneness = "[X] ";
        } else {
            doneness = "[ ] ";
        }
        String eventName = super.toString();
        String time = " (at: " + eventTime + ")";
        return box1 + doneness + eventName + time;
    }
}
