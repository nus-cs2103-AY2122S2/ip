/**
 * The type Event.
 */
public class Event extends Task {
    /**
     * The time that the task is supposed to be carried out at.
     */
    protected String at;

    /**
     * Instantiates a new Event.
     *
     * @param input the input
     */
    public Event(String eventName, String at) {
        super(eventName);
        this.at = at;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.at);
    }

}
