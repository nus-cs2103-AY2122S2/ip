package task;

import java.util.ArrayList;

public class Deadline extends Task {

    protected String by;

    /**
     * Default constructor for Deadline object.
     *
     * @param description description of Deadline to be added
     * @param by date that Deadline has to be completed by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns type of Task as a String.
     *
     * @return String form of Task's type
     */
    @Override
    public String getTaskType() {
        return "D";
    }

    /**
     * Prints Deadline details as a String.
     *
     * @return String form of Deadline details
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
