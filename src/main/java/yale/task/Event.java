package yale.task;

/**
 * Subclass of Task
 * Added modification of generic
 * Task object
 */
public class Event extends Task {
    /**
     * String to represent the date
     * and time of event
     */
    protected String at;

    /**
     * Constructor method
     * @param name
     * @param isMarked
     * @param at
     */
    public Event(String name, boolean isMarked, String at) {
        super(name, isMarked);
        this.at = at;
    }
    @Override
    public String export() {
        return "E " + "| " + (isMarked? 1 : 0) + " | " + this.name + " | " + this.at;
    }
    /**
     * Returns a customised String
     * @return Customised String format
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")"   ;
    }
}
