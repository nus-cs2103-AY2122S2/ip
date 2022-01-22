package tasks;

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
     * Aleternative Construcotr of Event to set completion status.
     * @param name
     * @param eventTime
     * @param isCompleted
     */
    public Event(String name, String eventTime, boolean isCompleted) {
        super(name, isCompleted);
        this.eventTime = eventTime;
    }

    /**
     * Returns the string representation of this event for storage.
     * @return
     */
    @Override
    public String toFileString() {
        return "E : " + (isCompleted ? "1 : " : "0 : ") + name + " : " + eventTime;
    }

    /**
     * Overriden toString method for Event.
     * @return String representation of Event.
     */
    @Override
    public String toString() {
        String str = "[E]";
        if (this.isCompleted) {
            str += "[X] ";
        } else {
            str += "[ ] ";
        }
        str += name + " ";
        str += "(at: " + eventTime + ")";
        return str;
    }
}
