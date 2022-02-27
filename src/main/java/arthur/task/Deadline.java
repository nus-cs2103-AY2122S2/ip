package arthur.task;

import arthur.timings.DateTime;

/**
 * A class that creates Task.Deadline objects with 'by' variable,
 * that stores the deadline information for that task with data/time.
 */
public class Deadline extends Task {
    private static final String LOGO = "[D]";
    private static final String REQ = "(By: ";
    private final DateTime timing;

    /**
     * Constructor for deadline object.
     *
     * @param e String to be created as task.
     * @param by String with the necessary timings.
     */
    public Deadline(String e, String by) {
        super(e);
        timing = new DateTime(by);
    }

    public DateTime getTiming() {
        return this.timing;
    }

    /**
     * Formats the string representation of the deadline object.
     *
     * @return A formatted string with the logo and details.
     */
    @Override
    public String toString() {
        return LOGO + super.toString() + REQ
                + this.timing.getString() + ")";
    }
}
