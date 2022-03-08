package duke;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a Deadline Task created by a user, modelled by an Object.
 */
public class Deadline extends Task {

    private Date date;
    private DateFormat formatter = new SimpleDateFormat("dd/MM/yy h:mm a");

    /**
     * The constructor for creating a deadline task.
     *
     * @param description the content of what the deadline is about.
     * @param date the date of the deadline of the task.
     * @param priority the priority level of this task (HIGH, MEDIUM, LOW).
     */
    public Deadline(String description, Date date, Priority priority) {
        super(description);
        this.date = date;
        this.priority = priority;
    }

    /**
     * The constructor for creating a deadline task.
     *
     * @param description the content of what the deadline is about.
     * @param hasCompleted a boolean variable to indicate if the task has been completed.
     * @param date the date of the deadline of the task.
     * @param priority the priority level of this task (HIGH, MEDIUM, LOW).
     */
    public Deadline(String description, boolean hasCompleted, Date date, Priority priority) {
        super(description);
        this.hasCompleted = hasCompleted;
        this.date = date;
        this.priority = priority;
    }

    /**
     * This method returns a string of the type of task this is.
     * @return a string that indicates what kind of task this is.
     */
    @Override
    public String toString() {
        return "[D]";
    }

    /**
     * This method returns the description of the deadline.
     * @return a string that describes the deadline.
     */
    @Override
    public String getDescription() {
        return this.description
                + " (by: " + formatter.format(date).toString() + ")";
    }
}
