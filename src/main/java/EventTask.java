/**
 * EventTask task which inherits from Task class.
 */
public class EventTask extends Task {

    /** Duration of event. */
    private String at;

    /**
     * Constructor for EventTask class.
     *
     * @param desc Description of EventTask.
     * @param at Duration of EventTask.
     */
    public EventTask(String desc, String at) {
        super(desc);
        this.at = at;
    }

    /**
     * String representation of EventTask for saving.
     *
     * @return String representation of EventTask for saving.
     */
    @Override
    public String saveFormat() {
        return "E | " + super.saveFormat() + " | " + at;
    }

    /**
     * String representation of EventTask.
     *
     * @return String representation of EventTask.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
