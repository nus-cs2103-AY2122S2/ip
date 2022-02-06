package duke.taskobjects;

/**
 * Representation of a Task that's an event
 */
public class Event extends TaskWithDate {
    /**
     * Default constructor for Event
     *
     * @param name Task name or description
     * @param time String representation of date
     */
    public Event(String name, String time) {
        super(name, time);
    }

    /**
     * Alternative constructor for Event used for importing existing task
     *
     * @param name Task name or description
     * @param isDone Boolean which shows if task is marked as done or not
     * @param time String representation of date
     */
    public Event(String name, boolean isDone, String time) {
        super(name, isDone, time);
    }

    @Override
    public String toString() {
        return super.toString() + "(at: " + super.getFormattedDate() + ")";
    }

    @Override
    public String getCurrentStatus() {
        return "[E]" + super.getCurrentStatus();
    }

    @Override
    public Types getType() {
        return Types.EVENT;
    }
}
