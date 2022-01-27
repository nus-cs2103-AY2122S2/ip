package lily.task;

/**
 * A Task which records the time of the event
 * 
 * @author Hong Yi En, Ian
 * @version Jan 2022 (AY21/22 Sem 2)
 */
public class Event extends Task {
    protected String at;

    /**
     * Create a Event object.
     * <p>
     * @param s The description of what is happening
     * @param at When the event is happening
     */
    public Event(String s, String at) throws LilyException {
        super(s);
        this.at = at;
    }

    /**
     * Returns the Event as a String 
     * @return Deadline in the form of "[E][ ] Description (at: due)"
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
} 