package duke.task;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a event task, which is a task occurring at a certain date and time.
 */
public class Event extends Task {
    private final Date time;

    /**
     * Constructs an Event task object.
     * @param title The name given to the task
     * @param time The date and time of the event
     * @param status Indicates whether the task has been completed or not
     */
    public Event(String title, Date time, boolean status) {
        super(title, status);
        this.time = time;
    }

    /**
     * Constructs an Event task object with the completion status initialised to false.
     * @param title The name given to the task
     * @param time The date and time of the event
     */
    public Event(String title, Date time) {
        super(title, false);
        this.time = time;
    }

    /**
     * Returns task name with the '[E]' prefix to identify it as a event
     * @return the task as a string
     */
    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("d/M/yy HH:mm");
        return "[E]" + super.toString() + "(at: " + formatter.format(time) + ")";
    }
}
