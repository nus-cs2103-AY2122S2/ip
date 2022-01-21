/**
 * This is a type of Task: Event
 *
 */
public class Event extends Task{

    /**
     * Constructor of event.
     * Have to strip/process the input first and remove 'event'
     *
     * @param input descriptor of task
     * @param number task number
     * @param time duration of event
     */
    public Event(String input, int number, String time, boolean reading) {
        super(input, number, time, "E", reading);
    }

    /**
     * Gets string representation of task.
     *
     * @return String representation of task.
     */
    @Override
    public String toString(){
        return String.format("%d. [E][%s] %s (at: %s)\n", number+1, getStatus(), name, this.time);
    }
}
