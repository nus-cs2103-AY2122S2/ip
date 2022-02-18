package duke;

/**
 * Represents a event task
 * It corresponds to a event represent by a string of task and the time
 *
 * @author brandonrhan
 * @version 0.0.0
 */
public class Event extends Task {

    /**
     * Constructor of event
     *
     * @param task description of the task
     * @param time time of the event
     */
    public Event(String task, String time) {
        super(task, "E", time);
    }

    /**
     * Override Task.saveFormat by adding the time of deadline
     *
     * @return String in the standard saving format
     */
    @Override
    public String saveFormat() {
        return super.saveFormat() + " ### " + this.getTime();
    }

    /**
     * Override Task.toString() by adding the date and convert into standard format
     *
     * @return String in the standard printing format
     */
    @Override
    public String toString() {
        return super.toString() + "(at: " + this.getDate() + ")" + "  " + this.getTag();
    }

}
