package Tasks;

/**
 * Event type of Task. Has specific time of occurence.
 */
public class Event extends Task{
    private String eventTime;

    /**
     * Constructor of Event.
     * @param name
     * @param eventTime
     */
    public Event(String name, String eventTime) {
        super(name);
        this.eventTime = eventTime;
    }

    /**
     * Overriden toString method for Event.
     * @return String representation of Event.
     */
    @Override
    public String toString() {
        String str = "[E]";
        if (this.completed) {
            str += "[X] ";
        } else {
            str += "[ ] ";
        }
        str += name + " ";
        str += "(at: " + eventTime + ")";
        return str;
    }
}
