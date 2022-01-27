package duke.task;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a deadline task, which is a task that must be completed by a certain date and time.
 */
public class Deadline extends Task {
    private final Date time;

    /**
     * Constructs a Deadline task object.
     *
     * @param title The name given to the task.
     * @param time The date and time of the deadline.
     * @param isComplete Indicates whether the task has been completed or not.
     */
    public Deadline(String title, Date time, boolean isComplete) {
        super(title, isComplete);
        this.time = time;
    }

    /**
     * Constructs a Deadline task object with the completion status initialised to false.
     *
     * @param title The name given to the task.
     * @param time The date and time of the deadline.
     */
    public Deadline(String title, Date time) {
        super(title, false);
        this.time = time;
    }

    /**
     * Returns task name with the '[D]' prefix to identify it as a deadline.
     *
     * @return the task as a string.
     */
    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("d/M/yy HH:mm");
        return "[D]" + super.toString() + "(by: " + formatter.format(time) + ")";
    }
}
