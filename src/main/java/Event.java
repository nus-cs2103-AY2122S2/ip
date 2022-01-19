/**
 * The Event class.
 * @author Jet Tan
 */
public class Event extends Task {
    protected String at;

    /**
     * Constructor for a new instance of Event, containing the description and date of the event.
     *
     * @param description The description of the event.
     * @param at The date of the event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns a string containing task type, done status and description along with time of event.
     *
     * @return string containing task type, done status and description along with time of event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + at + ")";
    }
}
