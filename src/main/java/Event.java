public class Event extends Task {
    /**
     * The time of this event
     */
    private String eventTime;

    /**
     * Constructor for Event objects
     *
     * @param taskName  the task name
     */
    public Event(String taskName, String time) {
        super(taskName);
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
