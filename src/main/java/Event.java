/**
 * Event task which inherits from Task class.
 */
public class Event extends Task {

    /** Duration of event. */
    private String at;

    /**
     * Constructor for Event class.
     *
     * @param desc Description of event task.
     * @param at Duration of event task.
     */
    public Event(String desc, String at) {
        super(desc);
        this.at = at;
    }

    /**
     * String representation of event task.
     *
     * @return String representation of event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
