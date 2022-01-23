package tasks;

/**
 * This class encapsulates an Event Task that occurs at a specific time frame.
 *
 * It has a start and end time.
 *
 * @author Ong Han Yang
 */
public class EventTask extends Task {
    /** start/end time of the event, temporarily as a String */
    private String startEndTime;

    /**
     * Constructs an Event Task.
     *
     * @param desc description for the task.
     * @param startEndTime the start/end time of the task.
     */
    public EventTask(String desc, String startEndTime) {
        super(desc);
        this.startEndTime = startEndTime;
    }

    /**
     * Produces an Event Task.
     *
     * @param desc description for the task.
     * @param startEndTime the start/end time of the task.
     * @return the Event Task.
     */
    public static EventTask of(String desc, String startEndTime) {
        return new EventTask(desc, startEndTime);
    }

    /**
     * Represents this EventTask as a String.
     *
     * @return the String representation of an EventTask.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), startEndTime);
    }
}
