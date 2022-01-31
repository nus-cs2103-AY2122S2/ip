package duke;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a Event Task created by a user, modelled by an Object.
 */

public class Event extends Task {

    private Date date;
    private DateFormat formatter = new SimpleDateFormat("dd/MM/yy h:mm a");


    /**
     * The constructor for creating an event task.
     *
     * @param description the content of what the deadline is about.
     * @param date the date of the event of the task.
     */
    public Event(String description, Date date) {
        super(description);
        this.date = date;
    }

    /**
     * The constructor for creating an event task.
     *
     * @param description the content of what the event is about.
     * @param hasCompleted a boolean variable to indicate if the task has been completed.
     * @param date the date of the event of the task.
     */
    public Event(String description, boolean hasCompleted, Date date) {
        super(description);
        this.hasCompleted = hasCompleted;
        this.date = date;

    }

    /**
     * This method returns a string of the type of task this is.
     * @return a string that indicates what kind of task this is.
     */
    @Override
    public String toString() {
        return "[" + Type.E + "]";
    }

    /**
     * This method returns the description of the deadline.
     * @return a string that describes the deadline.
     */
    @Override
    public String getDescription() {
        return this.description
                + " (at: " + formatter.format(date).toString() + ")";
    }
}