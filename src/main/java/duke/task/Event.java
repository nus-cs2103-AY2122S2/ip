package duke.task;

/**
 * A task that occurs at a location that has to be done.
 */
public class Event extends Task {

    private final String eventLocation;

    /**
     * Constructor for event task.
     *
     * @param activity the activity that needs to be done.
     * @param eventLocation the location of the event.
     */
    public Event(String activity, String eventLocation) {
        super(activity, "E");
        assert eventLocation != null;
        this.eventLocation = eventLocation;
    }

    /** {@inheritDoc} */
    @Override
    public String printTask() {
        if (this.isMarked) {
            return "[" + type + "][X] " + activity + " (at " + eventLocation + ")";
        } else {
            return "[" + type + "][ ] " + activity + " (at " + eventLocation + ")";
        }
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return type + "|" + isMarked + "|" + activity + "|" + eventLocation + "|\n";
    }

}
