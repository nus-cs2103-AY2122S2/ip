package duke.task;

/**
 * A task that occurs at a location that has to be done.
 */
public class Event extends Task {

    String at;

    /**
     * Constructor for event task.
     * @param activity the activity that needs to be done.
     * @param at the location of the event.
     */
    public Event(String activity, String at) {
        super(activity, "E");
        this.at = at;
    }

    /** {@inheritDoc} */
    @Override
    public String printTask() {
        if (this.isMarked) {
            return "[" + type + "][X] " + activity + " (at " + at + ")";
        } else {
            return "[" + type + "][ ] " + activity + " (at " + at + ")";
        }
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return type + "|" + isMarked + "|" + activity + "|" + at + "|\n";
    }

}
