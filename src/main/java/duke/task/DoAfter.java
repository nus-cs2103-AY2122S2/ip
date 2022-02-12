package duke.task;

/**
 * A task that occurs at a location that has to be done.
 */
public class DoAfter extends Task {

    private final String doAfterTime;

    /**
     * Constructor for event task.
     *
     * @param activity the activity that needs to be done.
     * @param doAfterTime the location of the event.
     */
    public DoAfter(String activity, String doAfterTime) {
        super(activity, "A");
        assert doAfterTime != null;
        this.doAfterTime = doAfterTime;
    }

    /** {@inheritDoc} */
    @Override
    public String printTask() {
        if (this.isMarked) {
            return "[" + type + "][X] " + activity + " (after " + doAfterTime + ")";
        } else {
            return "[" + type + "][ ] " + activity + " (after " + doAfterTime + ")";
        }
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return type + "|" + isMarked + "|" + activity + "|" + doAfterTime + "|\n";
    }

}
