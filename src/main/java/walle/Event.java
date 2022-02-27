package walle;

/**
 * This is a type of Task: duke.Event
 *
 */
public class Event extends Task {

    /**
     * Constructor of event.
     * Have to strip/process the input first and remove 'event'
     *
     * @param input descriptor of task
     * @param time duration of event
     * @param taskNum number representing the task in the list
     * @param isReading flag to check if input is being read from file data
     */
    public Event(String input, String time, int taskNum, boolean isReading) {
        super(input, taskNum, time, "E", isReading);
    }

    /**
     * Gets string representation of task.
     *
     * @return String representation of task.
     */
    @Override
    public String toString() {
        assert (name != "" || name != null) : "Invalid: Event has no name";
        assert (time != "" || time != null) : "Invalid: Event has no time";
        return String.format("%d. [E][%s] %s (at: %s)\n", number + 1, getStatus(), name, this.time);
    }
}
